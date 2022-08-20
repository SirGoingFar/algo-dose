package interviews.playtech;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Playtech {

    //1
    /**
     * Question: Build the DAG data structure as described in the screenshot located in the package
     */
    public static class Node {
        private final int identifier;
        private final List<Integer> direction;

        public Node(int identifier) {
            this.identifier = identifier;
            this.direction = new ArrayList<>();
        }

        public int getIdentifier() {
            return identifier;
        }

        public List<Integer> getDirection() {
            return direction;
        }

        public void addDirection(int identifier) {
            direction.add(identifier);
        }
    }

    public static class Graph {
        private final Set<Node> nodes;

        public Graph() {
            nodes = new HashSet<>();
        }

        public void add(int identifier) {

            //if identifier exist - throw
            boolean nodeExists = doesNodeExist(identifier);
            if (nodeExists) {
                throw new IllegalArgumentException("Node already exists in the graph");
            }

            nodes.add(new Node(identifier));
        }

        private boolean doesNodeExist(int identifier) {
            return (int) nodes.stream().filter(n -> n.getIdentifier() == identifier).count() > 0;
        }

        public Node getNode(int identifier) {
            return nodes.stream().filter(node -> node.getIdentifier() == identifier).collect(Collectors.toList()).get(0);
        }

        public void connect(int fromId, int toId) {

            if (!doesNodeExist(fromId) || !doesNodeExist(toId)) {
                throw new IllegalArgumentException("Either or both nodes does not exists");
            }

            if (fromId == toId) {
                throw new IllegalArgumentException("Node cannot point to itself");
            }

            Node fromNode = getNode(fromId);
            Node toNode = getNode(toId);

            //Check Direct Connection
            if (toNode.getDirection().contains(new Integer(fromNode.getIdentifier()))) {
                throw new IllegalArgumentException("Cyclic connection disallowed");
            }

            //Check tree-like connection
            List<Integer> reachableNodeIds = getAllReachableNodeIdsFrom(toNode);
            if (reachableNodeIds.contains(new Integer(fromId))) {
                throw new IllegalArgumentException("Tree cyclic connection disallowed");
            }

            fromNode.addDirection(toId);
        }

        public Set<Node> getNodes() {
            return nodes;
        }

        public List<Integer> getAllReachableNodeIdsFrom(Node target) {
            List<Integer> allNodeIds = new ArrayList<>(target.getDirection());
            if (target.getDirection().isEmpty()) {
                return new ArrayList<>();
            }

            for (Integer id : target.getDirection()) {
                allNodeIds.addAll(getAllReachableNodeIdsFrom(getNode(id)));
            }

            return allNodeIds;
        }

    }

    //2
    public class SystemDesign {

        /**
         *
         * Login
         *
         * Wallet service (credit/debit wallet, query wallet by Id)
         *
         * Game Server (/play -> determine WIN/LOSE, award money)
         *
         * Answers:
         * - How money should move from player account to operator account before play
         * - How player is settled based on play outcome
         * - How Wallet Service has to ensure post-play settlement idempotency to avoid double-disbursement in case of failure
         * - How Wallet Service has to lock in walletId when money movement is ongoing
         *
         * */

    }

}

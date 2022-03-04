package interviews.playtech;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PlaytechTest {

    @Test
    public void testGraph() {
        Playtech.Graph graph = new Playtech.Graph();
        graph.add(1);
        Assertions.assertEquals(1, graph.getNodes().size());
        Assertions.assertThrows(IllegalArgumentException.class, () -> graph.add(1));
    }

    @Test
    public void testConnect_nodeCannotConnectToItself() {
        Playtech.Graph graph = new Playtech.Graph();
        graph.add(1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> graph.connect(1, 1));
    }

    @Test
    public void testConnect_twoNodesMustNotFormDirectCycle() {
        Playtech.Graph graph = new Playtech.Graph();
        graph.add(1);
        graph.add(2);

        graph.connect(1, 2);
        Playtech.Node target = graph.getNode(1);
        Assertions.assertNotNull(target);
        Assertions.assertEquals(1, target.getDirection().size());

        //Avoid connection cycle
        Assertions.assertThrows(IllegalArgumentException.class, () -> graph.connect(2, 1));
    }

    @Test
    public void twoNodesMustNotFormTreeCycle() {
        Playtech.Graph graph = new Playtech.Graph();
        graph.add(1);
        graph.add(2);
        graph.add(3);

        graph.connect(1, 2);
        graph.connect(2, 3);

        //Avoid tree connection cycle
        Assertions.assertThrows(IllegalArgumentException.class, () -> graph.connect(3, 1));
    }
}
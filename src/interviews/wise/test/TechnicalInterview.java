package interviews.wise.test;


/**
 *
 *     Build a data import web service.

 * You need to build a web service which:
 * 1. reads a list of bank transaction details from a file (100 - millions)
 * 2. enriches this data using a remote API call
 * 3. stores the data in a relational database

 *     The web service provides an endpoint to accept the file and process it.

 *     The input file can be considered a CSV file where each row consists of a bank transaction ID, reference and state, like the following example:

 *     bank_transaction_id,reference,state
 *     1001,payment 1,SENT
 *     2001,payment 2,SENT
 *     3005,payment 3,IN_PROGRESS
 *     40404,payment 4,REJECTED

 *     The remote REST API takes the transaction reference and returns additional data on the payment, like the following:

 *     Request: GET /v1/transfers?reference="payment 1"
 *     Response: 200 OK
 *     {
 *         transferId: 123
 *         paymentType: “BILL"
 *         customerType: “BUSINESS”
 *     }
 *
 */
public class TechnicalInterview {

    @RestController
    @RequestMapping("/transsaction")
    public class ImportController {

        private FileImportUsecase fileImportUsecase;

        public ImportController(FileImportUsecase fileImportUsecase){
            //set property
        }

        @PostMapping(value = "/v1/import", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<ImportResponse> importFile(@RequestParam("file") MultipartFile file) {
            final ImportResponse importResponse = fileImportUsecase.execute(new FileImportCommand(file));
            return ResponseEntity.ok(importResponse);
        }

    }

    @Service
    class FileImportService implements FileImportUsecase{

        //Fetch more details about the paymentReference
        private FetchPaymentInfoPort fetchPaymentInfoPort;

        //Database Port
        private TransactionPort transactionPort;

        //Parse Multipart file to CSV
        private CsvParser csvParser;

        private ExecutorService executor;

        private Vector<Job> allJobs;

        @Tranactional(rollbackFor=" ")
            //implement constructor
        ImportResponse execute(FileImportCommand command){

            long processedFileCount = 0;

            while(processedFileCount < command.file.getSize()){

                //CSV Info Extraction
                Optional<List<PaymentInfo>> paymentListOptional = Optional.empty();

                try{
                    paymentListOptional = csvParser.parse(command.file, 500);
                }catch(BadFormatException ex){
                    throw new IllegalArgumentException(ex.getMessage());
                }

                if(paymentListOptional.isEmpty()){
                    throw new IllegalArgumentException("Message");
                }


                for(PaymentInfo paymentInfo : paymentListOptional.get()){

                    Job job = new Job(paymentInfo);
                    allJob.add(job);
                    executorService.submit()


                }

                processedFileCount += 500;
            }

            return

        }
    }

    class Job implements Runnable{
        private PaymentInfo payemntInfo;
        private boolean failed;


        @Override
        public void run() {
            addTransactionToDb(paymentInfo);
        }
    }

    public interface MultipartFile {

        boolean isEmpty();

        long getSize();

        byte[] getBytes();

        InputStream getInputStream();
    }

    interface FileImportUsecase{
        ImportReponse execute(FileImportCommand command);
    }

    class FileImportCommand{
        @NotNull(message="File is required")
        @NotEmpty(message="File cannot be empty")
        private MultipartFile multipartFile;

        //constructor
    }

    class PaymentInfo{
        private BigInteger bankTransactionId;
        private String transactionReference;
        private String state;
    }

    class TransactionModel{
        private BigInteger bankTransactionId;
        private String transactionReference;
        private String state;
    }

}

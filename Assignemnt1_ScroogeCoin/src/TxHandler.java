import org.omg.CORBA.TRANSACTION_MODE;

public class TxHandler {

    /**
     * Creates a public ledger whose current UTXOPool (collection of unspent transaction outputs) is
     * {@code utxoPool}. This should make a copy of utxoPool by using the UTXOPool(UTXOPool uPool)
     * constructor.
     */

    UTXOPool utxoPool;

    public TxHandler(UTXOPool utxoPool) {
        this.utxoPool = utxoPool;
    }

    /**
     * @return true if:
     * (1) all outputs claimed by {@code tx} are in the current UTXO pool, 
     * (2) the signatures on each input of {@code tx} are valid, 
     * (3) no UTXO is claimed multiple times by {@code tx},
     * (4) all of {@code tx}s output values are non-negative, and
     * (5) the sum of {@code tx}s input values is greater than or equal to the sum of its output
     *     values; and false otherwise.
     */
    public boolean isValidTx(Transaction tx) {

        boolean exist = true;
        boolean validsign = true;
        for(Transaction.Input input: tx.getInputs()) {
            UTXO temp = new UTXO(input.prevTxHash,input.outputIndex);
            if(!utxoPool.contains(temp)) {
                exist = false;
            }
        }
        for(Transaction.Input input: tx.getInputs()) {
            if(!Crypto.verifySignature(tx.getOutput(input.outputIndex).address,tx.getHash(),input.signature)) {
                validsign = validsign;
            }
        }
       return exist & validsign;


}

    /**
     * Handles each epoch by receiving an unordered array of proposed transactions, checking each
     * transaction for correctness, returning a mutually valid array of accepted transactions, and
     * updating the current UTXO pool as appropriate.
     */
    public Transaction[] handleTxs(Transaction[] possibleTxs) {


        return possibleTxs;
    }

}

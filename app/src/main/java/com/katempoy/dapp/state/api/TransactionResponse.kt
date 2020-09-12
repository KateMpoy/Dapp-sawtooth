package com.katempoy.dapp.state.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TransactionResponse(
      @field:SerializedName("data")
      @Expose
      val data: List<Trans>,

      @field:SerializedName("link")
      @Expose
      /**
       * Returns the link to query the batch status
       *
       * @return String link
       */
      val link: String) {
    override fun toString(): String {
        return "TransactionResponse(data=$data, link='$link')"
    }

}

class Trans(
    @field:SerializedName("id")
    @Expose
    /** Batch ID
     *
     * @return String bach id
     */
    val id: String,

    @field:SerializedName("status")
    @Expose
    /**
     * Status of the batch. Possible values are 'COMMITTED', 'INVALID', 'PENDING', and 'UNKNOWN'.
     *
     * @return String status
     */
    val status: String,

    @field:SerializedName("invalid_transactions")
    @Expose
    /**
     * List of InvalidTransactions if there's any
     *
     * @return List InvalidTransactions
     */
    val invalidTransactions: List<InvalidTransaction>

) {
    override fun toString(): String {
        return "Datum(id='$id', status='$status', invalidTransactions=$invalidTransactions)"
    }
}


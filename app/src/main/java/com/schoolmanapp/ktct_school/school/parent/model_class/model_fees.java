package com.schoolmanapp.ktct_school.school.parent.model_class;

import java.util.List;

/**
 * Created by srishtiinnovative on 12/01/18.
 */

public class model_fees {

    /**
     * status : true
     * msg : Successfull
     * Data : {"PaidHistoryData":[{"Billdate":"2018-01-12T10:40:07.603","BillNo":3,"PaidBillsData":[{"Particulars":"New Fee","Discount":0,"Amount":500}]},{"Billdate":"2018-01-12T10:40:28.98","BillNo":4,"PaidBillsData":[{"Particulars":"Uniform","Discount":0,"Amount":100}]}],"DueHistoryData":[{"Particulars":"Uniform Due","Details":"January","Amount":1900},{"Particulars":"Uniform","Details":"January","Amount":2000},{"Particulars":"Uniform","Details":"January","Amount":2000},{"Particulars":"Uniform","Details":"January","Amount":2000}]}
     */

    private boolean status;
    private String msg;
    private DataBean Data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean {
        private List<PaidHistoryDataBean> PaidHistoryData;
        private List<DueHistoryDataBean> DueHistoryData;

        public List<PaidHistoryDataBean> getPaidHistoryData() {
            return PaidHistoryData;
        }

        public void setPaidHistoryData(List<PaidHistoryDataBean> PaidHistoryData) {
            this.PaidHistoryData = PaidHistoryData;
        }

        public List<DueHistoryDataBean> getDueHistoryData() {
            return DueHistoryData;
        }

        public void setDueHistoryData(List<DueHistoryDataBean> DueHistoryData) {
            this.DueHistoryData = DueHistoryData;
        }

        public static class PaidHistoryDataBean {
            /**
             * Billdate : 2018-01-12T10:40:07.603
             * BillNo : 3
             * PaidBillsData : [{"Particulars":"New Fee","Discount":0,"Amount":500}]
             */

            private String Billdate;
            private int BillNo;
            private List<PaidBillsDataBean> PaidBillsData;

            public String getBilldate() {
                return Billdate;
            }

            public void setBilldate(String Billdate) {
                this.Billdate = Billdate;
            }

            public int getBillNo() {
                return BillNo;
            }

            public void setBillNo(int BillNo) {
                this.BillNo = BillNo;
            }

            public List<PaidBillsDataBean> getPaidBillsData() {
                return PaidBillsData;
            }

            public void setPaidBillsData(List<PaidBillsDataBean> PaidBillsData) {
                this.PaidBillsData = PaidBillsData;
            }

            public static class PaidBillsDataBean {
                /**
                 * Particulars : New Fee
                 * Discount : 0
                 * Amount : 500
                 */

                private String Particulars;
                private int Discount;
                private int Amount;

                public String getParticulars() {
                    return Particulars;
                }

                public void setParticulars(String Particulars) {
                    this.Particulars = Particulars;
                }

                public int getDiscount() {
                    return Discount;
                }

                public void setDiscount(int Discount) {
                    this.Discount = Discount;
                }

                public int getAmount() {
                    return Amount;
                }

                public void setAmount(int Amount) {
                    this.Amount = Amount;
                }
            }
        }

        public static class DueHistoryDataBean {
            /**
             * Particulars : Uniform Due
             * Details : January
             * Amount : 1900
             */

            private String Particulars;
            private String Details;
            private int Amount;

            public String getParticulars() {
                return Particulars;
            }

            public void setParticulars(String Particulars) {
                this.Particulars = Particulars;
            }

            public String getDetails() {
                return Details;
            }

            public void setDetails(String Details) {
                this.Details = Details;
            }

            public int getAmount() {
                return Amount;
            }

            public void setAmount(int Amount) {
                this.Amount = Amount;
            }
        }
    }
}

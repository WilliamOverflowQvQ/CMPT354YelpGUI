package com.williamoverflow.cmpt354yelpgui.filters;

// FilterComponent in each filter
public class FilterComponent {
    public boolean enabled = true;
    public boolean reverse = false; // when true ... NOT xxx ...
    public boolean proxyMatch = false;   // when true: name LIKE "%illiam"

    public boolean allowProxy = false;

    public CompType type = CompType.TEXT;

    public String name = "";
    public String sqlName = "";



    public FilterComponent(String name, String sqlName, CompType type, boolean allowProxy){
        this.name = name;
        this.sqlName = sqlName;
        this.type = type;
        this.allowProxy = allowProxy;
    }


    public String getFilterStringQuestioned(){
        if(!this.enabled){
            return "";
        }

        String res = "";
        switch(this.type){

            case TEXT -> {
                res += this.proxyMatch ? "LIKE ? " : "= ? ";
            }
            case BETWEEN -> {
                res += "BETWEEN ? AND ? ";
            }
            case VALUE -> {
                res += this.proxyMatch ? "= ? " : "= ? ";
            }
            case LARGER -> {
                res += this.proxyMatch ? "> ? " : ">= ? ";
            }
            case SMALLER -> {
                res += this.proxyMatch ? "< ? " : "<= ? ";
            }
        }
        res = sqlName + " " + res;
        res = (this.reverse ? "NOT " : "") + res;
        res = "AND " + res;
        return res;
    }


    public String getFilterStringFilled(String input1, String input2){
        if(!this.enabled){
            return " ";
        }

        String res = "";
        switch (this.type) {
            case TEXT -> {
                if(input1.isEmpty()){
                    res = " ";
                }
                else{
                    res += this.proxyMatch ? "LIKE " : "= ";
                    res += this.proxyMatch ?  "'" : "'%";
                    res += input1;
                    res += this.proxyMatch ?  "'" : "%' ";
                }
            }
            case BETWEEN -> {
                if(input1.isEmpty() && input2.isEmpty()){
                    res += " ";
                }
                else if(input1.isEmpty()){
                    res += this.proxyMatch ? "< " : "<= ";
                    res += input2 + " ";
                }
                else if(input2.isEmpty()){
                    res += this.proxyMatch ? "> " : ">= ";
                    res += input1 + " ";
                }
            }
            case VALUE -> {
                if(input1.isEmpty()){
                    res += " ";
                }else{
                    res += "= ";
                    res += input1 + " ";
                }
            }

        }
        if(this.reverse){
            res = "NOT " + res;
        }


        res = this.sqlName + " " + res;
        res = "AND " + res;
        return res;
    }


    public enum CompType {
        TEXT,
        BETWEEN,
        VALUE,
        LARGER,
        SMALLER,

    }
}

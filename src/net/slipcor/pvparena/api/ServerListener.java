package net.slipcor.pvparena.api;

public class ServerListener {

    public String ReplaceThisWithHttp;

    public ServerListener(){
        // initiate http
        this.ReplaceThisWithHttp = "http conn";
    }
    public boolean checkServerConn(){
        return true;
    }
    // send POST request to server, and receive a positive reply
    // return true if reply is positive
    public boolean sendRequest(String data){
        System.out.println(data);
        return true;
    }
}

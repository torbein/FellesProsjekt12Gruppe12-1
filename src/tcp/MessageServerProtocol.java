package tcp;

import java.util.Hashtable;

public class MessageServerProtocol {
    private String nick;
    private Clients conn;
 
    /* a hash table from user nicks to the corresponding connections */
    private static Hashtable<String, Clients> nicks = 
        new Hashtable<String, Clients>();
 
    private static final String msg_OK = "OK";
    private static final String msg_NICK_IN_USE = "Bruker er paa";
    private static final String msg_SPECIFY_NICK = "Spesifiser bruker";
    private static final String msg_INVALID = "ukjent kommando";
    private static final String msg_SEND_FAILED = "kunne ikke sende";
 
    /**
     * Adds a nick to the hash table 
     * returns false if the nick is already in the table, true otherwise
     */
    private static boolean add_nick(String nick, Clients c) {
        if (nicks.containsKey(nick)) {
            return false;
        } else {
            nicks.put(nick, c);
            return true;
        }
    }
 
    public MessageServerProtocol (Clients c) {
        nick = null;
        conn = c;
    }
    public void logOff(){
    	if(nicks.containsKey(nick)){
    		System.err.println(nick + " logged of");
    		nicks.remove(nick);
    	}
    }
 
    private void log(String msg) {
        System.err.println(msg);
    }
 
    public boolean isAuthenticated() {
        return ! (nick == null);
    }
 
    /**
     * Implements the authentication protocol.
     * This consists of checking that the message starts with the NICK command
     * and that the nick following it is not already in use.
     * returns: 
     *  msg_OK if authenticated
     *  msg_NICK_IN_USE if the specified nick is already in use
     *  msg_SPECIFY_NICK if the message does not start with the NICK command 
     */
    private String authenticate(String msg) {
        if(msg.startsWith("NICK")) {
            String tryNick = msg.substring(5);
            if(add_nick(tryNick, this.conn)) {
                log("Nick " + tryNick + " joined.");
                this.nick = tryNick;
                return msg_OK;
            } else {
                return msg_NICK_IN_USE;
            }
        } else {
            return msg_SPECIFY_NICK;
        }
    }
 
    /**
     * Send a message to another user.
     * @recepient contains the recepient's nick
     * @msg contains the message to send
     * return true if the nick is registered in the hash, false otherwise
     */
    private boolean sendMsg(String recipient, String msg) {
    	System.out.println(recipient);
    	System.out.println(msg);
    	if (nicks.containsKey(recipient)) {
            Clients c = nicks.get(recipient);
            c.sendMsg(nick + ": " + msg);
            System.out.println("halla");
            return true;
        } else {
            return false;
        }
    }
 
    /**
     * Process a message coming from the client
     */
    public String process(String msg) {
        if (!isAuthenticated()) 
            return authenticate(msg);
 
        String[] msg_parts = msg.split(" ", 3);
        String msg_type = msg_parts[0];
 
        if(msg_type.equals("MSG")) {
            if(msg_parts.length < 3) return msg_INVALID;
            if(sendMsg(msg_parts[1], msg_parts[2])) return msg_OK;
            else return msg_SEND_FAILED;
        } else {
            return msg_INVALID;
        }
    }
}
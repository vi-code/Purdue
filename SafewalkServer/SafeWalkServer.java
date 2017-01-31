/**
 * Project 5
 * @Vihar Patel, patel486@purdue.edu, LM1
 * @Gunjan Gauri, ggauri@purdue.edu, L07
 */



import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.net.*;

class CallServer {
    public String line; 
    public Socket c; 
    public PrintWriter output; 
    public BufferedReader input; 
    public String name; 
    public String from; 
    public String to; 
    
    //The arraylist of requests :3
    public CallServer(String line, Socket c, PrintWriter output, BufferedReader input) {
        this.line = line; 
        this.c = c;  
        this.output = output; 
        this.input = input; 
        String[] token = line.split(","); 
        this.name = token[0]; 
        this.from = token[1]; 
        this.to = token[2];
         
    }
    
}


public class SafeWalkServer extends ServerSocket implements Runnable{
    //ServerSocket server ;
    PrintWriter w = new PrintWriter(System.out);
    private ArrayList<CallServer> req = new ArrayList<CallServer>(); //created an array list called call server that stores the list of requests
    private boolean run = true; 
    
    /**
     * Construct the server, set up the socket.
     * 
     * @throws SocketException if the socket or port cannot be obtained properly.
     * @throws IOException if the port cannot be reused.
     */
    public SafeWalkServer(int port) throws SocketException,IOException {
        super(port); 
        String s = Integer.toString(port); 
    }
    
    public SafeWalkServer() throws SocketException,IOException {
        super(0);
        int port = getLocalPort();
        //get theport number if not specified.
        w.println("Port not specified. Using free port " + port + ".");
        w.flush();
        setReuseAddress(true);  
    }
    
    
    
    
    // Checks for validity of port within the range of 1025 and 65535 
    
    public static boolean isPortValid(String port) {
        PrintWriter pw = new PrintWriter(System.out);
        //pw.println("Enter isPortValid\n");
        //pw.flush();
        try {
            if ( port == null || port.trim().length() == 0) {
                //pw.println("Invalid port number");       
                //pw.flush();
                return false;
            }
            else {
                if (Integer.parseInt(port) >= 1025 && Integer.parseInt(port) <= 65535) {
                    // pw.printf("Valid port number: %s\n", port); 
                    //pw.flush();
                    return true;
                } else {
                    //   pw.printf("invalid port return false\n");
                    //  pw.flush();
                    return false;
                }
            }
        } catch (NumberFormatException e) {
            
            //pw.println("Retry");
            //pw.flush();
            return false;
        }
//        w.println("Invalid port number");
//        w.flush();
//        return false;
    }
    
    // returns boolean value for PENDING REQUEST
    
    public static boolean validRequest(String line)
    {
        String[] locationsTo = {"CL50" , "EE" , "LWSN" , "PMU", "PUSH" , "*" };
        String[] locationsFrom = {"CL50" , "EE" , "LWSN" , "PMU", "PUSH" };
        
        String[] token = line.split(",");
        String from = token[1] ;
        String to = token[2];
        int t = 0  ;
        int f = 0 ;
        
        
        if (token.length != 3) {
            return false;
        }
        
        for (int  i = 0 ; i< token.length ; i++) {
            if (token[i] == null) {
                return false;
            }
        }
        
        if (from.equals(to))  {
            return false;
        }
        
        for (int  i = 0 ; i< 6 ; i++) {
            if (to.equals(locationsTo[i])) {
                t = 1 ;
                // break;
            }
        }
        
        for (int  i = 0 ; i< 5 ; i++) {
            if (from.equals(locationsFrom[i])) {
                f = 1 ;
                // break;
            }
        }
        
        if (t + f == 2 ) {
            return true; 
        }
        
        return false;
        
    }
    // checks validity for "To location"
    public static boolean toValid(String input) {
        String[] locationsTo = {"CL50" , "EE" , "LWSN" , "PMU", "PUSH" , "*" };
        for (int i = 0; i < 6; i++) {
            if( input.equals(locationsTo[i])){
                return true;
            }
        }
        return false;  
    }
    // checks validity for "From location"
    public static boolean fromValid(String input) {
        String[] locationsFrom = {"CL50" , "EE" , "LWSN" , "PMU", "PUSH" };
        for (int i = 0; i < 5; i++) {
            if( input.equals(locationsFrom[i])){
                return true;
            }
        }
        return false;  
    }
    // Splits the name with a "," and returns the first element
    public static String splitName(String input) {
        //String from = "";
        String[] split = input.split(",");
        return split[0];
    }
    // Splits the name with a "," and returns the second element 
    public static String splitFrom(String input) {
        //String from = "";
        String[] split = input.split(",");
        return split[1];
    }
    // Splits the name with a "," and returns the third element
    public static String splitTo(String input) {
        String to = "";
        String[] split = input.split(",");
        return split[2];
    }
    
    // print elements of array split separated by comma
    public static String printResponse (String input) {
        String[] split = input.split(",");
        String name = split[0];
        String from = split[1];
        String to = split[2];
        String print = name + ", " + from + ", " + to;
        return print;
    }
    
    
    public void run() {
        while(run) {
            try {
                Socket c = accept();
                PrintWriter output = new PrintWriter(c.getOutputStream(), true);
                BufferedReader input = new BufferedReader(new InputStreamReader(c.getInputStream()));
                String line = (String) input.readLine();
                
                String [] p = line.split(","); //splits the input with comma
                String star1 = ":PENDING_REQUESTS,#,*,"; //"" + p[0] + ","+ p[1] + "," + p[2] + ",";
                String star2 = ":PENDING_REQUESTS,#,"; //"" + p[0] + "," +p[1] + ",";
                
                w.printf("Received: %s\n", line);
                w.flush();
                if(line.charAt(0) == (':'))
                {
                    
                    //switch(line) { switch case doesn't work with the various cases of pending requests
                    if( !p[0].equals(":RESET") && !p[0].equals(":SHUTDOWN") && !p[0].equals(":PENGING_REQUESTS")) {
                        output.println("ERROR: invalid command");
                        output.flush();
                    }
                    //RESET
                    if(line.equals(":RESET")) { 
                        if(req.size() > 0) {
                            for (int j = 0; j < req.size(); j++ ){
                                req.get(j).output.println("ERROR: connection reset");
                                req.get(j).output.flush();
                                req.get(j).c.close();
                            }
                        }
                        output.println("RESPONSE: success");
                        output.flush();
                        c.close();
                        req.clear();
                        //break;
                    }
                    //SHUTDOWN
                    else if ( line.equals(":SHUTDOWN")) {
                        String success = "RESPONSE: success";
                        if( req.size() > 0) {
                            for (int j = 0; j < req.size(); j++ ){
                                req.get(j).output.println("ERROR: connection reset");
                                req.get(j).output.flush();
                                req.get(j).c.close();
                            }
                        }
                        output.println(success);
                        output.flush();
                        c.close();
                        run = false;
                        //break;
                    }
                    //REQUESTS ( * * * )
                    else if(line.equals( ":PENDING_REQUESTS,*,*,*")) {
                        String list = "[";
                        int j = 0;
                        String request;
                        if(req.size() > 0) {
                            for ( j = 0; j < req.size()-1; j++) {
                                request = req.get(j).line;
                                request = printResponse(request);
                                list = list + String.format("[%s], " ,  request);
                                
                            } //while (j < req.size()-1 ); // listing from 1 to n-1
                            request = printResponse(req.get(j).line); 
                            list = list + String.format("[%s]" , request );// last element does not have a comma
                        }
                        list = list + "]";
                        
                        
                        output.println(list);
                        output.flush() ;
                        c.close();
                        //break;
                    }
                    //REQUESTS (# * *)
                    else if (line.equals( ":PENDING_REQUESTS,#,*,*" ) ) {
                        String r1 = String.format("RESPONSE: # of pending requests = %d", req.size());
                        output.println(r1);
                        output.flush() ;
                        //c.close();
                        //break;
                    }
                    
                    // Checks for valid command
                    else if (star1.equals( "" + p[0] + ","+ p[1] + "," + p[2] + "," )) {
                        if( !toValid(p[3])) {
                            output.println("ERROR: invalid command");
                            output.flush();
                        } 
                        else {
                            String []tok1 = line.split(",");
                            String to = tok1[3];
                            int count1 = 0;
                            for (int i = 0; i < req.size(); i++ ){
                                String request1 = req.get(i).line;
                                String [] token = request1.split(",") ;
                                if (to.equals(token[2])) {
                                    count1++;
                                }
                                
                            }
                            // The response of pending requests
                            String r2 = String.format("RESPONSE: # of pending requests to %s = %d", to, count1);
                            output.println(r2);
                            output.flush() ;
                            c.close();
                            //break;
                        }
                    }
                    // check for invalid command
                    else if (star2.equals( "" + p[0] + "," +p[1] + "," ) && p[3].equals("*")) {
                        if( !fromValid(p[2])) {
                            output.println("ERROR: invalid command");
                            output.flush();
                        }
                        else {
                            String []tok2 = line.split(",");
                            String from = tok2[2];
                            int count2 = 0;
                            for (int i = 0; i < req.size(); i++ ){
                                String request2 = req.get(i).line;
                                String [] token = request2.split(",") ;
                                if (from.equals(token[1])) {
                                    count2++;
                                }
                                
                            }
                            String r3 = String.format("RESPONSE: # of pending requests from %s = %d", from, count2);
                            output.println(r3);
                            output.flush() ;
                            c.close();
                            //break;
                        }
                    }
                    else {
                        output.println("ERROR: invalid command");
                        output.flush();
                        c.close();
                        //break;                  
                    }
                    
                    
                    
                }
                else {
                    if (validRequest(line)) {
                        //w.println(line);
                        //w.flush();
                        req.add(new CallServer(line, c, output, input));
                        int latest = req.size() - 1;
                        String cf = req.get(latest).from;
                        String ct  = req.get(latest).to;
                        CallServer call  = req.get(latest);
                        CallServer match;
                        for(int i = 0 ; i < latest ; i++) {
                            // This was if( req.get(i).from.equals(cf) && ct.equals("*")  ||  
                            // req.get(i).from.equals(cf) && req.get(i).to.equals(ct)) || 
                            // ( req.get(i).from.equals(cf) && req.get(i).to.equals("*"))){
                            // but it gave an error so split into an if-else-if statements
                            if( req.get(i).from.equals(cf) && ct.equals("*")){
                                if(req.get(i).to.equals("*"))
                                    continue; // 2 volunteers cannot be matched.
                                else {
                                    match = req.get(i);
                                    String mr = String.format("RESPONSE: %s" , call.line);
                                    match.output.println(mr);
                                    match.output.flush();
                                    String cr =  String.format("RESPONSE: %s" , match.line);
                                    call.output.println(cr);
                                    call.output.flush();
                                    req.remove(i);
                                    req.remove(latest-1);
                                    
                                    break;
                                }
                                
                            }
                            
                            else if(( req.get(i).from.equals(cf) && req.get(i).to.equals(ct))) {
                                
                                if(ct.equals("*")) {
                                    continue; // vounteers cannot be matched 
                                }else {
                                    match = req.get(i);
                                    String mr = String.format("RESPONSE: %s" , call.line);
                                    match.output.println(mr);
                                    match.output.flush();
                                    String cr =  String.format("RESPONSE: %s" , match.line);
                                    call.output.println(cr);
                                    call.output.flush();
                                    req.remove(i);
                                    req.remove(latest-1);
                                    break;
                                }
                            }
                            else if( ( req.get(i).from.equals(cf) && req.get(i).to.equals("*"))) {
                                if(ct.equals("*")) {
                                    continue; // vounteers cannot be matched 
                                }else {
                                    match = req.get(i);
                                    String mr = String.format("RESPONSE: %s" , call.line);
                                    match.output.println(mr);
                                    match.output.flush();
                                    String cr =  String.format("RESPONSE: %s" , match.line);
                                    call.output.println(cr);
                                    call.output.flush();
                                    req.remove(i);
                                    req.remove(latest-1);
                                    req.remove(i);
                                    break;
                                }
                            }
                        }
                        
                        
                    }
                    else {
                        output.println("ERROR: invalid request");
                        output.flush();
                        c.close();
                    }
                    
                }
            }catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
    
    
    
    
    public static void main(String args[]) throws SocketException, IOException {
        
        
        SafeWalkServer s ;
        PrintWriter p = new PrintWriter(System.out);
        if(args.length !=0) {
            String str = args[0];
            if(isPortValid(str)==true) {
                s = new SafeWalkServer(Integer.parseInt(str));
                s.run();
            }//exception for if port in use
            else {
                p.println("Error: Invalid Port");
                p.flush();
            }
        }
        else{
            s = new SafeWalkServer();
            
            s.run();
        }
    }
}



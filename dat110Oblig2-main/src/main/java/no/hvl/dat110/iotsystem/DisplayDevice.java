package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.messages.PublishMsg;

public class DisplayDevice {
    
    private static final int COUNT = 10;
    
    public static void main (String[] args) {
        
        System.out.println("Display starting ...");
        
        // create a client object and use it to
        // - connect to the broker - use "display" as the username
        // - create the temperature topic on the broker
        // - subscribe to the topic
        // - receive messages on the topic
        // - unsubscribe from the topic
        // - disconnect from the broker
        
     // Creating a client object
        Client client = new Client("display", Common.BROKERHOST, Common.BROKERPORT);
        
        try {
            // Connect to the broker
            client.connect();
            
            // Create the temperature topic on the broker
            client.createTopic(Common.TEMPTOPIC);
            
            // Subscribe to the topic
            client.subscribe(Common.TEMPTOPIC);
            
            // Receive messages on the topic
            for (int i = 0; i < COUNT; i++) {
                // Receive a message
                PublishMsg msg = (PublishMsg) client.receive();
                
                // Display the received message
                System.out.println("Received message: " + msg.getMessage());
            }
            
            // Unsubscribe from the topic
            client.unsubscribe(Common.TEMPTOPIC);
            
            // Disconnect from the broker
            client.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("Display stopping ... ");
    }
}

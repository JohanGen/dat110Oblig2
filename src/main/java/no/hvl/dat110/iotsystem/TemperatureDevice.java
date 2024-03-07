package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;

public class TemperatureDevice {

    private static final int COUNT = 10;

    public static void main(String[] args) {

        // simulated / virtual temperature sensor
        TemperatureSensor sn = new TemperatureSensor();

        // create a client object and use it to
        // - connect to the broker - user "sensor" as the user name
        // - publish the temperature(s)
        // - disconnect from the broker

        // Creating a client object
        Client client = new Client("sensor", Common.BROKERHOST, Common.BROKERPORT);

        try {
            // Connect to the broker
            client.connect();

            // Publish the temperature(s)
            for (int i = 0; i < COUNT; i++) {
                // Simulate getting temperature reading
                float temperature = sn.read();
                // Publish the temperature reading
                client.publish(Common.TEMPTOPIC, String.valueOf(temperature));
                // Sleep for a while before publishing the next reading
                Thread.sleep(1000);
            }

            // Disconnect from the broker
            client.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Temperature device stopping ... ");
    }
}

/**

 * @author 12296309
 * Student Name: MD SAKIB UL ISLAM
 */
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Simple console menu for MAP stats.
 */
public class View {
    private final MAPAnalyser analyser;

    public View(MAPAnalyser analyser) {
        this.analyser = analyser;
    }

    /**
     * Reads commands until the user exits.
     */
    public void commandLoop() {
        Scanner sc = new Scanner(System.in);
        printMenu();
        boolean exit = false;

        while (!exit) {
            System.out.print("> ");
            String line = sc.nextLine().trim();
            if (line.isEmpty()) {
                printInvalid();
                continue;
            }
            String[] parts = line.split("\\s+");
            String option = parts[0];

            switch (option) {
                case "0":
                    printMenu();
                    break;
                case "1":
                    handleOption1(parts);
                    break;
                case "2":
                    handleOption2(parts);
                    break;
                case "3":
                    System.out.println("(trace) Option 3 Chosen: Statistics");
                    handleOption3();
                    break;
                case "4":
                    System.out.println("(trace) Option 4 Chosen: Display all records");
                    handleOption4();
                    break;
                case "9":
                    exit = true;
                    break;
                default:
                    printInvalid();
                    break;
            }
        }
    }

    /**
     * Option 1: show record by ID.
     */
    private void handleOption1(String[] parts) {
        if (parts.length < 2) {
            // Phase 2B / Alternative wording
            System.out.println("Option 1 needs a patientID after it.");
            return;
        }
        System.out.println("(trace) Option 1 chosen, id: " + parts[1]);

        PatientRecord r = analyser.find(parts[1]);
        if (r == null) {
            System.out.println("Patient record not found.");
        } else {
            System.out.println(r);
        }
    }

    /**
     * Option 2: show records in MAP range.
     */
    private void handleOption2(String[] parts) {
        if (parts.length < 3) {
            // Phase 2B / Alternative wording
            System.out.println("Option 2 expects two values: map1 and map2.");
            return;
        }
        try {
            int m1 = Integer.parseInt(parts[1]);
            int m2 = Integer.parseInt(parts[2]);

            System.out.println("(trace) Option 2 chosen, range = [" + m1 + " .. " + m2 + "]");

            if (m1 > m2 || m1 < 0 || m2 > 200) {
                System.out.println("Range entered is not valid.");
                System.out.println("Rules: map1 should be <= map2, map1 >= 0, and map2 <= 200.");
                return;
            }

            ArrayList<PatientRecord> out = analyser.find(m1, m2);
            if (out.isEmpty()) {
                System.out.println("No patient records found in the specified range.");
            } else {
                for (PatientRecord pr : out) System.out.println(pr);
            }
        } catch (NumberFormatException e) {
            System.out.println("Both map1 and map2 need to be whole numbers.");
        }
    }

    /**
     * Option 3: print stats.
     */
    private void handleOption3() {
        System.out.printf("Min MAP: %d%n", analyser.minMAP());
        System.out.printf("Max MAP: %d%n", analyser.maxMAP());
        System.out.printf("Average MAP:


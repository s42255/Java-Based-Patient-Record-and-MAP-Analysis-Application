/**
 * @author 12296309
 * Student Name: MD SAKIB UL ISLAM
 */
/**
 * Starts the MAP app.
 */
public class Assignment1 {
    public static void main(String[] args) {
        MAPAnalyser analyser = new MAPAnalyser();
        View view = new View(analyser);
        view.commandLoop();
    }
}

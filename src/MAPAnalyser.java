import java.util.ArrayList;
/**
 * @author 12296309
 * Student Name: MD SAKIB UL ISLAM
 */
/**
 * Works with patient blood pressure records and MAP stats.
 */
public class MAPAnalyser {
    private PatientRecord[] data;
    private int numRecords;

    /**
     * Load data and sort by ID.
     */
    public MAPAnalyser() {
        loadFromTables();
        sortById();
    }

    /**
     * Loads sample data (IDs, SBP, DBP) and computes MAP.
     * MAP formula: (SBP + 2*DBP) / 3
     */
    private void loadFromTables() {
        String[] ids = { "S20", "S10", "S35", "S45", "S15", "S25" };
        int[] sbp =    {   70,   100,   120,   140,   150,   100 };
        int[] dbp =    {   50,    70,    80,    90,   100,    22 };

        data = new PatientRecord[ids.length];
        for (int i = 0; i < ids.length; i++) {
            int map = (int)((sbp[i] + 2 * dbp[i]) / 3.0);
            data[i] = new PatientRecord(ids[i], sbp[i], dbp[i], map, classifyMAP(map));
        }
        numRecords = data.length;
    }

    /**
     * Returns "low", "normal" or "high" based on MAP.
     */
    private String classifyMAP(int map) {
        if (map < 70) return "low";
        if (map <= 100) return "normal";
        return "high";
    }

    /** @return minimum MAP, or 0 if none */
    public int minMAP() {
        if (numRecords == 0) return 0;
        int min = data[0].getMap();
        for (int i = 1; i < numRecords; i++) {
            if (data[i].getMap() < min) min = data[i].getMap();
        }
        return min;
    }

    /** @return maximum MAP, or 0 if none */
    public int maxMAP() {
        if (numRecords == 0) return 0;
        int max = data[0].getMap();
        for (int i = 1; i < numRecords; i++) {
            if (data[i].getMap() > max) max = data[i].getMap();
        }
        return max;
    }

    /** @return average MAP, or 0.0 if none */
    public double averageMAP() {
        if (numRecords == 0) return 0.0;
        int sum = 0;
        for (PatientRecord r : data) sum += r.getMap();
        return (double) sum / numRecords;
    }

    /**
     * Median of MAP values (keeps original order by sorting a copy).
     */
    public double medianMAP() {
        if (numRecords == 0) return 0.0;

        PatientRecord[] copy = data.clone();
        // selection sort by MAP
        for (int i = 0; i < copy.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < copy.length; j++) {
                if (copy[j].getMap() < copy[minIdx].getMap()) minIdx = j;
            }
            PatientRecord t = copy[i];
            copy[i] = copy[minIdx];
            copy[minIdx] = t;
        }

        if (numRecords % 2 == 1) {
            return copy[numRecords / 2].getMap();
        } else {
            int a = copy[numRecords / 2 - 1].getMap();
            int b = copy[numRecords / 2].getMap();
            return (a + b) / 2.0;
        }
    }

    /**
     * Binary search by patient ID (data is sorted by ID).
     */
    public PatientRecord find(String id) {
        int lo = 0, hi = numRecords - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int cmp = data[mid].getId().compareTo(id);
            if (cmp == 0) return data[mid];
            if (cmp < 0) lo = mid + 1; else hi = mid - 1;
        }
        return null;
    }

    /**
     * Linear scan for records with MAP in [m1, m2].
     */
    public ArrayList<PatientRecord> find(int m1, int m2) {
        ArrayList<PatientRecord> out = new ArrayList<>();
        for (int i = 0; i < numRecords; i++) {
            int m = data[i].getMap();
            if (m >= m1 && m <= m2) out.add(data[i]);
        }
        return out;
    }

    /**
     * Selection sort by ID (A–Z).
     */
    private void sortById() {
        for (int i = 0; i < numRecords - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < numRecords; j++) {
                if (data[j].getId().compareTo(data[minIdx].getId()) < 0) minIdx = j;
            }
            PatientRecord t = data[i];
            data[i] = data[minIdx];
            data[minIdx] = t;
        }
    }

    public int getNumRecords() { return numRecords; }
    public PatientRecord[] getPatientData() { return data; }
}

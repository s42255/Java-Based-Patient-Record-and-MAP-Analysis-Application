/**
 * @author 12296309
 * Student Name: MD SAKIB UL ISLAM
 */
/**
 * One patient's BP and MAP info.
 */
public class PatientRecord {
    private final String id;
    private final int sbp;
    private final int dbp;
    private final int map;
    private final String category;

    public PatientRecord(String id, int sbp, int dbp, int map, String category) {
        this.id = id;
        this.sbp = sbp;
        this.dbp = dbp;
        this.map = map;
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("<%s %5d %5d %5d %s>", id, sbp, dbp, map, category);
    }

    public String getId() { return id; }
    public int getSbp() { return sbp; }
    public int getDbp() { return dbp; }
    public int getMap() { return map; }
    public String getCategory() { return category; }
}


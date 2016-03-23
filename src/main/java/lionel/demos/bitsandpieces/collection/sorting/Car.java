package lionel.demos.bitsandpieces.collection.sorting;

public class Car {

    public Car() {
        this.randomBuild();
    }
    
    public final void randomBuild(){
        this.setColor((String) getRandomValue(PROP.color));
        this.setDoors((Short) getRandomValue(PROP.doors));
        this.setMake((String) getRandomValue(PROP.make));
        this.setMileage((Integer) getRandomValue(PROP.mileage));
        this.setSize((Double) getRandomValue(PROP.size));
    }

    @Override
    public String toString() {
        /* return "Make: " + make+ ", Color: " + color+", Mileage: " + mileage
         + ", Size: " + size + ", Doors: " + doors;*/
        return boxedVal(make) + boxedVal(color) + boxedVal(mileage + "")
                + boxedVal(size + "") + boxedVal(doors + "") + "|";
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public short getDoors() {
        return doors;
    }

    public void setDoors(short doors) {
        this.doors = doors;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    private String make, color;
    private short doors = 5;
    private double size;
    private int mileage;

    private enum PROP {

        make, color, doors, size, mileage
    };

    private static Object getRandomValue(PROP property) {
        switch (property) {
            case color:
                return getColor(nBetween1To5());
            case make:
                return getMake(nBetween1To5());
            case doors:
                return getDoors(nBetween1To5());
            case size:
                return getSize(nBetween1To5());
            default:
                return getMileage(nBetween1To5());
        }
    }

    public static String boxedVal(String val) {
        if (val == null) {
            val = "NULL";
        }
        StringBuilder sb = new StringBuilder("|" + val.toUpperCase());
        int l = 10 - val.length();
        for (int i = 0; i < l; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    private static int nBetween1To5() {
        int n = -1;
        do {
            double rand = Math.round(Math.random() * 10);
            n = (int) rand;
        } while (n < 1 || n > 5);
        return n;
    }

    private static Integer getMileage(int rand) {
        switch (rand) {
            case 1:
                return 100000;
            case 2:
                return 76000;
            case 3:
                return 54000;
            case 4:
                return 42000;
            default:
                return 20000;
        }
    }

    private static Double getSize(int rand) {
        switch (rand) {
            case 1:
                return 1.2;
            case 2:
                return 1.4;
            case 3:
                return 1.6;
            case 4:
                return 1.8;
            default:
                return (double) 2;
        }
    }

    private static Short getDoors(int rand) {
        switch (rand) {
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            default:
                return 5;
        }
    }

    private static String getColor(int rand) {
        switch (rand) {
            case 1:
                return "red";
            case 2:
                return "silver";
            case 3:
                return "black";
            case 4:
                return "white";
            default:
                return "blue";
        }
    }

    private static String getMake(int rand) {
        switch (rand) {
            case 1:
                return "Honda";
            case 2:
                return "Audi";
            case 3:
                return "Nissan";
            case 4:
                return "Renault";
            default:
                return "BMW";
        }
    }
}

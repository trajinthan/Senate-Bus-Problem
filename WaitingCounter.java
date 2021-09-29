public class WaitingCounter {
    private int count;

    public WaitingCounter(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    // reasign a value to the counter
    public void setCount(int count) {
        this.count = count;
    }

    // add 1 to the present value
    public void incrementCount() {
        this.count += 1;
    }

    // subtract 1 from present value
    public void decrementCount() {
        this.count -= 1;
    }

    public String countToString() {
        return "No. of waiting riders: " + getCount();
    }
}

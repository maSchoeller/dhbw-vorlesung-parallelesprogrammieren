package de.dhbw.parprog;

public class CalcResult {
    public final double avgAge;
    public final long maxLen;
    public final long maleCount;

    public CalcResult(double avgAge, long maxLen, long maleCount) {
        this.avgAge = avgAge;
        this.maxLen = maxLen;
        this.maleCount = maleCount;
    }

    @Override
    public String toString() {
        return "CalcResult{" +
                "avgAge=" + avgAge +
                ", maxLen=" + maxLen +
                ", maleCount=" + maleCount +
                '}';
    }
}

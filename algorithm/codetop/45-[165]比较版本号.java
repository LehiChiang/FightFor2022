package codetop;

class compareVersionSolution {
    public int compareVersion(String version1, String version2) {
        int m = version1.length(), n = version2.length();
        int i = 0, j = 0;
        while (i < m || j < n) {
            int num1 = 0;
            for (; i < m && version1.charAt(i) != '.'; i++)
                num1  = num1 * 10 + version1.charAt(i) - '0';
            i++;
            int num2 = 0;
            for (; j < n && version2.charAt(j) != '.'; j++)
                num2 = num2 * 10 + version2.charAt(j) - '0';
            j++;
            if (num1 != num2)
                return num1 > num2 ? 1 : -1;
        }
        return 0;
    }

    public static void main(String[] args) {
        compareVersionSolution solution = new compareVersionSolution();
        System.out.println(solution.compareVersion("007.5.2.4", "07.5.2.4"));
    }
}

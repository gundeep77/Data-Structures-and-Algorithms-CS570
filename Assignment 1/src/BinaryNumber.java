public class BinaryNumber {

    private int data[];
    private boolean overflow;

    public BinaryNumber(int length) {
        if (length < 0) {
            System.out.println("Length of binary number cant't be negative!");
            System.exit(0);
        }

        else {
            data = new int[length];
            for (int i = 0; i < length; i++) {
                data[i] = 0;
            }
        }
    }

    public BinaryNumber(String str) {
        boolean valid = true;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '0' && str.charAt(i) != '1') {
                System.out.println(str + " is not a valid binary number!");
                valid = false;
                System.exit(0);
            }
        }
        if (valid) {
            data = new int[str.length()];
            for (int i = 0; i < str.length(); i++) {
                data[i] = Character.getNumericValue(str.charAt(i));
            }
        }
    }

    public int getLength() {
        return data.length;
    }

    public int getDigit(int index) {
        if (index > data.length - 1 || index < 0) {
            System.out.println("Index out of bounds!");
            return 0;
        } else
            return data[index];
    }

    public void shiftR(int amoutToShift) {
        if (amoutToShift < 0)
            System.out.println("Shift amount can't be negative!");
        else {
            int temp[] = new int[amoutToShift + data.length];
            for (int i = amoutToShift; i < temp.length; i++) {
                temp[i] = data[i - amoutToShift];
            }
            String str = "";
            for (int i : temp) {
                str += i;
            }
            System.out.println("The new binary number after shifting is: " + str);
        }
    }

    public void add(BinaryNumber aBinaryNumber) {
        if (this.getLength() != aBinaryNumber.getLength())
            System.out.println("Lengths of both binary numbers should be equal!");
        else {
            int carry = 0;
            System.out.print("Sum of " + this.toString() + " and " + aBinaryNumber + " = ");
            for (int i = this.getLength() - 1; i > -1; i--) {
                int sum = 0;
                sum = this.data[i] + aBinaryNumber.getDigit(i) + carry;

                if (sum == 0) {
                    sum = 0;
                    carry = 0;
                } else if (sum == 1) {
                    sum = 1;
                    carry = 0;
                } else if (sum == 2) {
                    sum = 0;
                    carry = 1;
                } else if (sum == 3) {
                    sum = 1;
                    carry = 1;
                }
                data[i] = sum;
            }
            if (carry == 1)
                overflow = true;
            System.out.println(toString());
        }
    }

    public String toString() {
        if (overflow)
            return "Overflow";
        else {
            String str = "";
            for (int digit : data) {
                str += digit;
            }
            return str;
        }
    }

    public int toDecimal() {
        int decimal_val = 0;
        for (int i = data.length - 1; i > -1; i--) {
            decimal_val += data[i] * Math.pow(2, data.length - 1 - i);
        }
        return decimal_val;
    }

    public void clearOverflow() {
        overflow = false;
        System.out.println("Overflow flag is cleared now!");
    }

    public static void main(String[] args) {
        BinaryNumber bn1 = new BinaryNumber("1011");
        BinaryNumber bn2 = new BinaryNumber("0010");
        bn1.add(bn2);
        System.out.println(bn1.getLength());
        System.out.println(bn2.getDigit(2));
        bn1.clearOverflow();
        BinaryNumber bn3 = new BinaryNumber("0011");
        bn2.add(bn3);
        System.out.println(bn1.toDecimal());
        bn1.shiftR(3);
    }
}
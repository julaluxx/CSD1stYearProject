import java.util.Scanner;

public class InvestmentRiskCalculator {

    private static double principal;
    private static double expectedReturn;
    private static double standardDeviation;
    private static int investmentPeriod;
    private static String again;
    private static String txt;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("โปรแกรมคำนวณความเสี่ยงในการลงทุน");
        System.out.println("========================================");

        // ลูปจะรับค่าจากผู้ใช้จนกว่า again จะเป็น n
        while (true) {
            // ลูปนี้เอาไว้ดักการป้อนข้อมูลผิดประเภท
            while (true) {
                System.out.print("\nป้อนจำนวนเงินทุน: ");
                if (input.hasNextDouble()) {
                    principal = input.nextDouble();
                    break; // ออกจากลูปเมื่อป้อนข้อมูลถูกต้อง
                } else {
                    System.out.println("กรุณาป้อนข้อมูลให้ถูกต้อง (ต้องเป็นตัวเลข)!");
                    input.nextLine(); // อ่านข้อมูลใหม่
                }
            }

            while (true) {
                System.out.print("\nป้อนผลตอบแทนต่อปีที่คาดหวัง (เปอร์เซ็นต์): ");
                if (input.hasNextDouble()) {
                    expectedReturn = input.nextDouble() / 100.0;
                    break;
                } else {
                    System.out.println("กรุณาป้อนข้อมูลให้ถูกต้อง (ต้องเป็นตัวเลข)!");
                    input.nextLine();
                }
            }

            while (true) {
                System.out.print("\nป้อนส่วนเบี่ยงเบนมาตรฐาน (เปอร์เซ็นต์): ");
                if (input.hasNextDouble()) {
                    standardDeviation = input.nextDouble() / 100.0;
                    break;
                } else {
                    System.out.println("กรุณาป้อนข้อมูลให้ถูกต้อง (ต้องเป็นตัวเลข)!");
                    input.nextLine();
                }
            }
            while (true) {
                System.out.print("\nป้อนระยะเวลาการลงทุน (ปี): ");
                if (input.hasNextInt()) {
                    investmentPeriod = input.nextInt();
                    break;
                } else if (input.hasNextDouble()) {
                    System.out.println("ควรป้อนระยะเวลาการลงทุนเป็นปี ลองใหม่อีกครั้ง");
                    input.next();
                } else {
                    System.out.println("กรุณาป้อนข้อมูลให้ถูกต้อง (ต้องเป็นตัวเลข)!");
                    input.nextLine();
                }
            }

            // แสดงผลรับการคำนวณ
            System.out.println("\n-----> ");
            // โดยเรียกใช้เมธอด calculateRisk()
            calculateRisk(principal, expectedReturn, standardDeviation, investmentPeriod, txt);

            // ลูปนี้เอาไว้ดักคำตอบของ again เพื่อให้ตอบแค่ y หรือ n เท่านั้น
            // ไม่งั้นก็ติดลูปอยู่ในนี้จนกว่าจะตอบถูก
            while (true) {
                System.out.println("\n--------------------");
                System.out.print("\nวางแผนการลงทุนใหม่หรือไม่? (y/n): ");
                again = input.next().toLowerCase();
                if (again.equals("y")) { // ตอบ y ก็ออกจากลูป แล้วไปรันรับค่าใหม่ตั้งแต่ต้น
                    break;
                } else if (again.equals("n")) { // ตอบ n ก็ออกจากโปรแกรมไปเลย ไม่ทำต่อแล้ว
                    System.out.println("\n=====> ขอบคุณที่ใช้บริการ สวัสดี");
                    input.close();
                    System.exit(0);
                } else { // ตอบมั่ว ไม่ตอบตามคำสั่งก็ติดลูปจ้ะ
                    System.out.println("คุณทำรายการไม่ถูกต้อง กรุณาลองใหม่");
                    continue;
                }
            }
        }
    }

    // หาความเสี่ยงในการลงทุนจากข้อมูลที่ผู้ใช้ป้อนเข้ามา
    public static void calculateRisk(double principal, double expectedReturn, double standardDeviation,
            int investmentPeriod, String txt) {

        // สูตรคำนวณเงินที่อาจสูญเสียไปในการลงทุน (ขอเรียกสั้นๆ ว่า เงินสูญ)
        double risk = principal * Math.pow(expectedReturn, investmentPeriod) * standardDeviation;

        // นำเงินสูญจากการลงทุน(risk) มาแปลงให้เป็นเปอร์เซ็นของเงินทุน
        double riskPercentage = (risk / principal) * 100;

        System.out.printf("คุณมีโอกาสสูญเงินในการลงทุน %.2f บาท\n", risk);
        
        // นำเปอร์เซ็นความเสี่ยงมาจัดประเภท โดยกำหนดมาตรฐานขึ้นมาเองโดยผู้พัฒนา
        if (riskPercentage < 20) {
            txt = "มีความเสี่ยงน้อยมาก";
        } else if (riskPercentage < 40) {
            txt = "มีความเสี่ยงน้อย";
        } else if (riskPercentage < 60) {
            txt = "มีความเสี่ยงปานกลาง";
        } else if (riskPercentage < 80) {
            txt = "มีความเสี่ยงมาก";
        } else if (riskPercentage <= 100) {
            txt = "มีความเสี่ยงมากที่สุด";
        }
        
        System.out.printf("คิดเป็น %.2f%% ของเงินทั้งหมด : %s", riskPercentage, txt);
    }

}

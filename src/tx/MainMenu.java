package tx;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Date;
import java.text.SimpleDateFormat;

public class MainMenu {
    static List<Bills> billsList = new ArrayList<>();
    static {
        billsList.add(new Bills("eating", "BOA", "expense", 500, "2022-11-7", "d1"));
        billsList.add(new Bills("living", "cash", "expense", 300, "2022-11-5", "d22"));
        billsList.add(new Bills("salary", "BOA", "income", 600, "2022-11-9", "d333"));
        billsList.add(new Bills("transport", "BOA", "expense", 100, "2022-11-9", "d4444"));
        billsList.add(new Bills("rent", "cash", "expense", 2000, "2022-11-9", "d55555"));
    }

    public static void main(String[] args) {
        run();
    }

    public static void showMain() {
        System.out.println("----------------You need a budget----------------");
        System.out.println("1.add  2.delete  3.search  4.exit");
        System.out.println("please input the function number");
    }

    public static void run() {
        showMain();

        boolean flag = true;
        Scanner scanner = new Scanner(System.in);

        while (flag) {
            int op = scanner.nextInt();
            switch (op) {
                case 1:
                    addBills();
                    break;
                case 2:
                    delBills();
                    break;
                case 3:
                    select();
                    break;
                case 4:
                    flag = false;
                    break;
                default:
                    System.out.println("please re-input");
            }
        }
        System.out.println("Exit. Goodbye!");
    }

    private static void addBills() {
        Scanner scanner = new Scanner(System.in);
        Bills bill = new Bills();
        System.out.println("please input name");
        bill.setName(scanner.next());
        System.out.println("please input account");
        bill.setAccount(scanner.next());
        System.out.println("please input type");
        bill.setType(scanner.next());
        System.out.println("please input total");
        bill.setTotal(scanner.nextDouble());
        System.out.println("please input time");
        bill.setTime(scanner.next());
        System.out.println("please input desc");
        bill.setDesc(scanner.next());

        billsList.add(bill);
        System.out.println("add successfully!");

        showMain();
    }

    private static void delBills() {
        System.out.println("please input delete id");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        billsList.remove(id-1);
        System.out.println("delete successfully!");
        showMain();
    }

    private static void select() {
        System.out.println("1.all  2.by date  3.by type");
        Scanner scanner = new Scanner(System.in);
        int op = scanner.nextInt();
        switch (op) {
            case 1:
                selectAll();
                break;
            case 2:
                selectByDate();
                break;
            case 3:
                selectByType();
                break;
        }
        showMain();
    }

    private static void selectByType() {
        System.out.println("please input which type (income/expense)");
        Scanner scanner = new Scanner(System.in);
        String type = scanner.next();
        List<Bills> billList = billsList.stream().filter(
                bills -> {
                    String type1 = bills.getType();
                    return type1.equals(type);
                }).collect(Collectors.toList());
        print(billList);
    }

    private static void selectByDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please input start date");
        String start = scanner.next();
        System.out.println("please input end date");
        String end = scanner.next();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        List<Bills> billList = billsList.stream().filter(
                bills -> {
                    String time = bills.getTime();
                    try{
                        Date startDate = formatter.parse(start);
                        Date endDate = formatter.parse(end);
                        Date timeDate = formatter.parse(time);
                        if(timeDate.before(endDate) && timeDate.after(startDate)) {
                            return true;
                        }
                    } catch (ParseException e){
                        e.printStackTrace();
                    }
                    return false;
                }).collect(Collectors.toList());
        print(billList);
    }

    private static void selectAll() {
        print(billsList);
    }

    public static void print(List<Bills> billsList){
        System.out.println("ID\t\tName\t\tAccount\t\tType\t\tTotal\t\tTime\t\tDesc");
        for (int i = 0; i < billsList.size(); i++) {
            Bills bills = billsList.get(i);
            System.out.println(i+1 + "\t\t" + bills.getName() + "\t\t" + bills.getAccount() + "\t\t" + bills.getType() + "\t\t" + bills.getTotal() + "\t\t" + bills.getTime() + "\t\t" + bills.getDesc());
        }
    }
}

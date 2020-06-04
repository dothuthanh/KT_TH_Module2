package app;

import app.PhoneBook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu extends PhoneBook {
    public ArrayList<PhoneBook> arrayList = new ArrayList<>();

    public void menu() {
        System.out.println("================");
        System.out.println("Menu:");
        System.out.println("Xem : 1");
        System.out.println("Thêm: 2");
        System.out.println("Sửa : 3");
        System.out.println("Xóa : 4");
        System.out.println("Tìm : 5");
        System.out.println("Đọc : 6");
        System.out.println("Ghi : 7");
        System.out.println("exit: 8");
        System.out.println("================");
        System.out.println("Lua tron Chuc Nang :");
        Scanner scanner = new Scanner(System.in);
        int value = scanner.nextInt();

        switch (value) {
            case 1:
                if (arrayList.isEmpty()) {
                    System.out.println(" danh ba trống:");
                    menu();
                    break;
                } else {
                    arrayList.forEach(k -> System.out.println(k));
                    menu();
                    break;
                }
            case 2:
                System.out.println("them 1 Danh ba moi");
                addDanhBa();
                System.out.println("----------------");
                menu();
                break;
            case 3:
                System.out.println("Cập Nhật!");
                update(searchDanhBa(scanner.nextInt()));
                menu();
                break;
            case 4:
                System.out.println("Xóa!");
                delete(searchDanhBa(scanner.nextInt()));
                menu();
                break;
            case 5:
                System.out.println("Nhap SDT can tim : ");
                searchDanhBa(scanner.nextInt());
                System.out.println("----------------");
                menu();
                break;
            case 6:
                System.out.println("đọc File!");
                menu();
                break;
            case 7:
                System.out.println("ghi File!");
                menu();
                break;
            case 8:
                System.exit(Integer.parseInt("kết thúc"));
            default:
                System.out.println("Chon Sai Moi Tron Lai!");
                menu();
                break;
        }
    }

    private PhoneBook inputFromKeyboard() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" AddPhone : ");
        int phone = scanner.nextInt();
        scanner.nextLine();

        System.out.println("AddTem : ");
        String tem = scanner.nextLine();

        System.out.println("AddName: ");
        String name = scanner.nextLine();

        System.out.println("AddGrender : ");
        String gender = scanner.nextLine();

        System.out.println("Address : ");
        String address = scanner.nextLine();

        System.out.println("addDateOfBirth : ");
        String dateOfBirth = scanner.nextLine();

        System.out.println("email : ");
        String email = scanner.nextLine();

        return new PhoneBook(phone, tem, name, gender, address, dateOfBirth, email);
    }

    public PhoneBook addDanhBa() {
        PhoneBook danhBa;
        danhBa = inputFromKeyboard();
        arrayList.add(danhBa);
        return danhBa;
    }

    public int searchDanhBa(int phone) {
        int index=0;
        boolean check = false;
        PhoneBook checkDanhBa = null;
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getPhone() == phone) {
                checkDanhBa = arrayList.get(i);
                check = true;
                index=i;
            } else {
                check = false;
            }
        }
        if (check) {
            System.out.println(" Tìm Thấy Số Phone : " + phone);

        } else {
            System.out.println("Không Tìm Thấy...!");
        }
        return index;

    }

    public void update(int i) {
        System.out.println("Nhập Lại Thông Tin Muốn Sửa....!");
        arrayList.set(i, inputFromKeyboard());
        System.out.println(" Thông Tin sau Khi Xửa : ");
        for (PhoneBook list : arrayList) {
            System.out.println(list);
            menu();
        }
    }

    public void delete(int i){
        System.out.println(" Xóa Danh Bạ: ");
        arrayList.remove(i);
        menu();

    }


    public void writerToFile(ArrayList<PhoneBook> arrayList, String path) {
        FileOutputStream outputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            outputStream = new FileOutputStream(path);
            objectOutputStream = new ObjectOutputStream(outputStream);

            System.out.println("Writing....!");
            for (PhoneBook danhBa : arrayList) {
                objectOutputStream.writeObject(danhBa);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<PhoneBook> read(String path) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream;
        List<PhoneBook> list = new ArrayList<>();
        try {
            fileInputStream = new FileInputStream(path);
            objectInputStream = new ObjectInputStream(fileInputStream);

            PhoneBook danhBa = null;
            while ((danhBa = (PhoneBook) objectInputStream.readObject()) != null) {
                list.add(danhBa);
//                arrayList.add(danhBa);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

}
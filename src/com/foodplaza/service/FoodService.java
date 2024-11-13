package com.foodplaza.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.foodplaza.daoimpl.FoodDaoImpl;
import com.foodplaza.pojo.Food;

public class FoodService {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		int choice;
		boolean flag;
		int foodId;
		String foodName,foodCategory,foodType;
		double foodPrice;
		
		List<Food>foodlist = new ArrayList<>();
		
		FoodDaoImpl fdi = new FoodDaoImpl();
		
		Food f = new Food();
		
		do
		{
			System.out.println("1.Add Food\n2.Update Food\n3.Delete Food\n4.Display Food by Food Id\n5.Display Food by Food Name\n6.Display Food by Price\n7.Display All Food\n8.Exit\nEnter your choice\n");
			
			choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				
				System.out.println("Enter Food Name");
				foodName = sc.next();
				
				System.out.println("Enter Food Price");
				foodPrice = sc.nextDouble();
				
				System.out.println("Enter Food Category");
				foodCategory = sc.next();
				
				System.out.println("Enter Food Type");
				foodType = sc.next();
				
				f.setFoodName(foodName);
				f.setFoodCategory(foodCategory);
				f.setFoodPrice(foodPrice);
				f.setFoodType(foodType);
				
				flag = fdi.addFood(f);
				
				if(flag) {
					System.out.println("Food Added Successfully");
				}
				else {
					System.out.println("Something went Wrong");
				}
				
				break;
				
			case 2:
				System.out.println("enter the food id: ");
				foodId = sc.nextInt();
				System.out.println("enter the food name: ");
				foodName = sc.next();
				System.out.println("enter the food price: ");
				foodPrice = sc.nextInt();
				System.out.println("enter the food catagory: ");
				foodCategory = sc.next();
				System.out.println("enter the food type: ");
				foodType = sc.next();
				
				f.setFoodId(foodId);
				f.setFoodName(foodName);
				f.setFoodPrice(foodPrice);
				f.setFoodCategory(foodCategory);
				f.setFoodType(foodType);
				
				flag = fdi.updateFood(f);
				
				if(flag)
				{
					System.out.println("successfully food updated.......");
				}
				else
				{
					System.out.println("spmething wents wrong........");
				}
				break;
				
			case 3:
				System.out.println("enter the food id: ");
				foodId = sc.nextInt();
				
				flag = fdi.deleteFood(foodId);
				
				if(flag)
				{
					System.out.println("successfully food deleted.........");
				}
				else
				{
					System.out.println("spmething wents wrong........");
				}
				break;
				
			case 4:
				System.out.println("Enter Food Id: ");
				foodId = sc.nextInt();
				
				f = fdi.displayFood(foodId);
				
				System.out.println(f.getFoodName()+" "+f.getFoodCategory()+" "+f.getFoodType()+" "+f.getFoodPrice());
				break;
				
			case 5:
				System.out.println("Enter Food Name");
				foodName = sc.next();
				
				f = fdi.displayFood(foodName);
				
				System.out.println(f.getFoodId()+" "+f.getFoodName()+" "+f.getFoodCategory()+" "+f.getFoodType()+" "+f.getFoodPrice());
				
			case 6:
				System.out.println("Enter the price: ");
				foodPrice = sc.nextDouble();
				foodlist = fdi.displayFood(foodPrice);
				
				for(Food f1 : foodlist)
				{

					System.out.println(f1.getFoodId()+" "+f1.getFoodName() +" "+ f1.getFoodCategory() +" "+ f1.getFoodPrice()+" "+f1.getFoodType());
				}
				break;
				
			case 7:
				foodlist = fdi.displayAllFood();
				for(Food f1 : foodlist)
				{

					System.out.println(f1.getFoodId()+" "+f1.getFoodName() +" "+ f1.getFoodCategory() +" "+ f1.getFoodPrice()+" "+f1.getFoodType());
				}
				break;
			}
		}
		while(choice!=5);
	}

}

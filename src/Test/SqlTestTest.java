package Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lin
 */
public class SqlTestTest {
        public static void main(String[] args){
            SqlTest sqltest=new SqlTest();
            String[] s=sqltest.getNames();
            for(int i=0; i<s.length; i++){
                System.out.println(s[i]);
            }
        }
}

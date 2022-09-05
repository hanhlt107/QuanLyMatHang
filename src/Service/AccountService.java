/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Repository.AccountRepository;

/**
 *
 * @author Admin
 */
public class AccountService {
    private AccountRepository accountRepository;

    public AccountService() {
        accountRepository = new AccountRepository();
    }
    public Integer getRole(String userName,String pass){
        return accountRepository.getRole(userName, pass);
    }
    
}

package bao.jt.tong.service;

import org.springframework.stereotype.Service;

@Service
public class TestService {
    public void fun(String name){
        for(int i=0;i<=2000000000;i++){
            System.out.println(name+"--"+i);
        }
    }
}

package cn.cleir.home.controller;


import cn.cleir.home.domain.Bus;
import cn.cleir.home.repository.HomeRepository;
import cn.cleir.home.until.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/homes")
public class HomeController {

    @Autowired
    private HomeRepository homeRepository;

    private String appcode = "08660ae3c8004e7cb59db8cefb437b7d";

    /**
     * 公交地铁查询
     */
    @PostMapping("/goto")
    public Bus getSubway(@Valid String city, @Valid String address){
        String host = "https://jisugjdt.market.alicloudapi.com";
        String path = "/transit/nearby";
        String method = "GET";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("address", address);
        querys.put("city", city);

        //插入数据库
        Bus bus = new Bus();
        bus.setAddress(address);
        bus.setCity(city);
        bus.setDate(new Date());

        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            //获取response的body
            String result = EntityUtils.toString(response.getEntity());
            bus.setResult(result);

            //System.out.println(result);
            return homeRepository.save(bus);
        } catch (Exception e) {
            e.printStackTrace();
            return homeRepository.save(bus);
        }

    }

}

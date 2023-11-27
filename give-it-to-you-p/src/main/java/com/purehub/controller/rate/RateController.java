package com.purehub.controller.rate;

import com.purehub.pojo.GenericLoanApplication;
import com.purehub.pojo.PersonalProductInterestRate;
import com.purehub.pojo.ProductInterestRate;
import com.purehub.pojo.Result;
import com.purehub.service.rate.GenericLoanApplicationService;
import com.purehub.service.rate.ProductInterestRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class RateController {

    @Autowired
    private ProductInterestRateService productInterestRateService;

    @Autowired
    private GenericLoanApplicationService genericLoanApplicationService;

    @GetMapping("/rate/product")
    public List<ProductInterestRate> getAllProductInterestRate() {
        System.out.println("### getAllProductInterestRate");
        return productInterestRateService.list();
    }

    @GetMapping("/rate/personalProduct")
    public Result getPerSonalProductInterestRate() { //获取个性化产品的利率
        List<PersonalProductInterestRate> list = genericLoanApplicationService.getPersonalProductRate();
        return Result.success(list);
    }

    @PostMapping("/rate/insertPersonal")
    public Result InsertPersonal(@RequestBody GenericLoanApplication loanApplication) {
        System.out.println(loanApplication);
        Boolean flag = genericLoanApplicationService.InsertGenericLoan(loanApplication);
        if (flag) {
            return Result.success();
        }
        else {
          return Result.error("插入失败");
        }
    }
}

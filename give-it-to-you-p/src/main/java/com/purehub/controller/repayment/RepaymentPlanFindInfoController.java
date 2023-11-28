package com.purehub.controller.repayment;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.purehub.pojo.GenericLoanApplication;
import com.purehub.pojo.RepaymentResult;
import com.purehub.service.Repayment.RepaymentPlanFindInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RepaymentPlanFindInfoController {
    @Autowired
    private RepaymentPlanFindInfoService repaymentPlanFindInfoService;
    @GetMapping("/findProductInfoByApplicationId")
    public RepaymentResult findProductInfo(@RequestParam Long applicationId)
    {
        QueryWrapper<GenericLoanApplication> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("application_id", applicationId);
        try{
            GenericLoanApplication genericLoanApplication = repaymentPlanFindInfoService.getOne(queryWrapper);
            return new RepaymentResult().success(genericLoanApplication, "查询成功");
        }catch(Exception e)
        {
            e.printStackTrace();
            return new RepaymentResult().error("查询失败");
        }
    }
}

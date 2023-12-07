package com.purehub.controller.record;

import com.purehub.service.record.RepaymentRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RepaymentRecordController {

  @Autowired
  private RepaymentRecordService repaymentRecordService;
}

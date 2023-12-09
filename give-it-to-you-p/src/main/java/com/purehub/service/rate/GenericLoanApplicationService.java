package com.purehub.service.rate;

import com.purehub.pojo.LoanApplication;
import com.purehub.pojo.PersonalProductInterestRate;

import java.util.List;

/**
 * #author 龚圆康
 * #class 软件2144
 * #student_number 21040147
 */
public interface GenericLoanApplicationService {
    List<PersonalProductInterestRate> getPersonalProductRate();

    Boolean InsertGenericLoan(LoanApplication loanApplication);
}

package com.jayway.market_express.twilio;

import com.jayway.market_express.common.util.EnvironmentUtil;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import static com.jayway.market_express.common.util.StringUtil.formatCellphoneFromPeru;

@Repository
@Slf4j
public class TwilioRepositoryImpl implements TwilioRepository {
    private final EnvironmentUtil environmentUtil;

    public TwilioRepositoryImpl(EnvironmentUtil environmentUtil) {
        this.environmentUtil = environmentUtil;
        Twilio.init(environmentUtil.getAccountSid(), environmentUtil.getAuthToken());
    }

    @Override
    public void sendSms(String to, String body) {
        to = formatCellphoneFromPeru(to);
        Message message = Message.creator(
                new PhoneNumber(to),
                new PhoneNumber(environmentUtil.getFromNumber()),
                body
        ).create();
        log.info("Message SID: " + message.getSid());
    }
}

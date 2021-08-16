package com.dxc.application.controllers;

import com.dxc.application.model.Combo;
import com.dxc.application.services.CommonService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ComboController.class)
public class ComboControlerMockTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommonService commonService;

    @Test
    @DisplayName("Test get Gim Type Combo must have 3 items")
    @SneakyThrows
    void testGimTypeComboMustHave3Items() {
        List<Combo> gimTypeList = new ArrayList<>();
        gimTypeList.add(new Combo("ACTIVE_FLAG", "ACTIVE_FLAG"));
        gimTypeList.add(new Combo("TEST", "TEST"));
        gimTypeList.add(new Combo("STATUS", "STATUS"));
        when(commonService.getGimTypeCombo()).thenReturn(gimTypeList);
        this.mockMvc.perform(get("/combo/gimtypecombo")).andDo(print()).andExpect(status().isOk());
    }

}

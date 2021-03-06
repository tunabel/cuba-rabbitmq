package com.company.rabbitmq.web.screens.agency;

import com.company.rabbitmq.entity.Agency;
import com.haulmont.cuba.gui.screen.*;

@UiController("rabbitmq_Agency.browse")
@UiDescriptor("agency-browse.xml")
@LookupComponent("agenciesTable")
@LoadDataBeforeShow
public class AgencyBrowse extends StandardLookup<Agency> {
}
<template>
  <div>
    <b-dropdown-item to="/op-site-qualification">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.opSiteQualification')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/pi">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.pi')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/monday-user">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.mondayUser')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/product-finished">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.productFinished')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/supplier">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.supplier')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/item">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.item')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/purchase-return">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.purchaseReturn')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/process-log">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.processLog')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/quotation">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.quotation')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/order-follow-up">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.orderFollowUp')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/operation-site-auditing">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.operationSiteAuditing')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/supplier-qualification">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.supplierQualification')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/uom">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.uom')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/forwarder-booking">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.forwarderBooking')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/product-taxmonomy">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.productTaxmonomy')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/commercial-invoice">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.commercialInvoice')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/forwarder">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.forwarder')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/inspection">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.inspection')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/operation-site">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.operationSite')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/group">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.group')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/sales-delivery">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.salesDelivery')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/client">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.client')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/monday-column">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.mondayColumn')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/authority">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.authority')"></span>
    </b-dropdown-item>
    <!-- jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here -->
  </div>
</template>

<script lang="ts" src="./entities-menu.component.ts"></script>

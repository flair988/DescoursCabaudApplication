<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="salesDelivery">
        <h2 class="jh-entity-heading" data-cy="salesDeliveryDetailsHeading">
          <span v-text="t$('descoursCabaudApplicationApp.salesDelivery.detail.title')"></span> {{ salesDelivery.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.salesDelivery.itemName')"></span>
          </dt>
          <dd>
            <span>{{ salesDelivery.itemName }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.salesDelivery.kingdeeId')"></span>
          </dt>
          <dd>
            <span>{{ salesDelivery.kingdeeId }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.salesDelivery.customer')"></span>
          </dt>
          <dd>
            <span>{{ salesDelivery.customer }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.salesDelivery.orderDate')"></span>
          </dt>
          <dd>
            <span>{{ salesDelivery.orderDate }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.salesDelivery.loadingPort')"></span>
          </dt>
          <dd>
            <span>{{ salesDelivery.loadingPort }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.salesDelivery.dischargePort')"></span>
          </dt>
          <dd>
            <span>{{ salesDelivery.dischargePort }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.salesDelivery.transportMode')"></span>
          </dt>
          <dd>
            <span>{{ salesDelivery.transportMode }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.salesDelivery.incoterm')"></span>
          </dt>
          <dd>
            <span>{{ salesDelivery.incoterm }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.salesDelivery.forwarder')"></span>
          </dt>
          <dd>
            <span>{{ salesDelivery.forwarder }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.salesDelivery.eta')"></span>
          </dt>
          <dd>
            <span>{{ salesDelivery.eta }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.salesDelivery.etd')"></span>
          </dt>
          <dd>
            <span>{{ salesDelivery.etd }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.salesDelivery.containerType')"></span>
          </dt>
          <dd>
            <span>{{ salesDelivery.containerType }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.salesDelivery.containerSize')"></span>
          </dt>
          <dd>
            <span>{{ salesDelivery.containerSize }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.salesDelivery.remark')"></span>
          </dt>
          <dd>
            <span>{{ salesDelivery.remark }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.salesDelivery.kingdeeUniqueId')"></span>
          </dt>
          <dd>
            <span>{{ salesDelivery.kingdeeUniqueId }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.salesDelivery.docStatus')"></span>
          </dt>
          <dd>
            <span>{{ salesDelivery.docStatus }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.salesDelivery.cateGory')"></span>
          </dt>
          <dd>
            <span>{{ salesDelivery.cateGory }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="salesDelivery.id"
          :to="{ name: 'SalesDeliveryEdit', params: { salesDeliveryId: salesDelivery.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./sales-delivery-details.component.ts"></script>

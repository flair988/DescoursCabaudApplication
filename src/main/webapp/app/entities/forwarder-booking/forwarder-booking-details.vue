<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="forwarderBooking">
        <h2 class="jh-entity-heading" data-cy="forwarderBookingDetailsHeading">
          <span v-text="t$('descoursCabaudApplicationApp.forwarderBooking.detail.title')"></span> {{ forwarderBooking.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.forwarderBooking.itemName')"></span>
          </dt>
          <dd>
            <span>{{ forwarderBooking.itemName }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.forwarderBooking.kingdeeId')"></span>
          </dt>
          <dd>
            <span>{{ forwarderBooking.kingdeeId }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.forwarderBooking.customer')"></span>
          </dt>
          <dd>
            <span>{{ forwarderBooking.customer }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.forwarderBooking.orderDate')"></span>
          </dt>
          <dd>
            <span>{{ forwarderBooking.orderDate }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.forwarderBooking.forwarder')"></span>
          </dt>
          <dd>
            <span>{{ forwarderBooking.forwarder }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.forwarderBooking.totalQty')"></span>
          </dt>
          <dd>
            <span>{{ forwarderBooking.totalQty }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.forwarderBooking.loadingPort')"></span>
          </dt>
          <dd>
            <span>{{ forwarderBooking.loadingPort }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.forwarderBooking.dischargePort')"></span>
          </dt>
          <dd>
            <span>{{ forwarderBooking.dischargePort }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.forwarderBooking.containerType')"></span>
          </dt>
          <dd>
            <span>{{ forwarderBooking.containerType }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.forwarderBooking.containerSize')"></span>
          </dt>
          <dd>
            <span>{{ forwarderBooking.containerSize }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.forwarderBooking.containerNumber')"></span>
          </dt>
          <dd>
            <span>{{ forwarderBooking.containerNumber }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.forwarderBooking.supplier')"></span>
          </dt>
          <dd>
            <span>{{ forwarderBooking.supplier }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.forwarderBooking.supplierEmail')"></span>
          </dt>
          <dd>
            <span>{{ forwarderBooking.supplierEmail }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.forwarderBooking.eta')"></span>
          </dt>
          <dd>
            <span>{{ forwarderBooking.eta }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.forwarderBooking.etd')"></span>
          </dt>
          <dd>
            <span>{{ forwarderBooking.etd }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.forwarderBooking.transportMode')"></span>
          </dt>
          <dd>
            <span>{{ forwarderBooking.transportMode }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.forwarderBooking.numberOfCartons')"></span>
          </dt>
          <dd>
            <span>{{ forwarderBooking.numberOfCartons }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.forwarderBooking.numberOfRef')"></span>
          </dt>
          <dd>
            <span>{{ forwarderBooking.numberOfRef }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.forwarderBooking.totalVolume')"></span>
          </dt>
          <dd>
            <span>{{ forwarderBooking.totalVolume }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.forwarderBooking.totalWeight')"></span>
          </dt>
          <dd>
            <span>{{ forwarderBooking.totalWeight }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.forwarderBooking.remark')"></span>
          </dt>
          <dd>
            <span>{{ forwarderBooking.remark }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.forwarderBooking.client')"></span>
          </dt>
          <dd>
            <span>{{ forwarderBooking.client }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.forwarderBooking.kingdeeUniqueId')"></span>
          </dt>
          <dd>
            <span>{{ forwarderBooking.kingdeeUniqueId }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="forwarderBooking.id"
          :to="{ name: 'ForwarderBookingEdit', params: { forwarderBookingId: forwarderBooking.id } }"
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

<script lang="ts" src="./forwarder-booking-details.component.ts"></script>

<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="pI">
        <h2 class="jh-entity-heading" data-cy="pIDetailsHeading">
          <span v-text="t$('descoursCabaudApplicationApp.pI.detail.title')"></span> {{ pI.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.pI.itemName')"></span>
          </dt>
          <dd>
            <span>{{ pI.itemName }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.pI.poNumber')"></span>
          </dt>
          <dd>
            <span>{{ pI.poNumber }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.pI.category')"></span>
          </dt>
          <dd>
            <span>{{ pI.category }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.pI.client')"></span>
          </dt>
          <dd>
            <span>{{ pI.client }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.pI.orderDate')"></span>
          </dt>
          <dd>
            <span>{{ pI.orderDate }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.pI.requestedEndOfProdDate')"></span>
          </dt>
          <dd>
            <span>{{ pI.requestedEndOfProdDate }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.pI.countryOfOrigin')"></span>
          </dt>
          <dd>
            <span>{{ pI.countryOfOrigin }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.pI.countryOfFinalDestination')"></span>
          </dt>
          <dd>
            <span>{{ pI.countryOfFinalDestination }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.pI.consignee')"></span>
          </dt>
          <dd>
            <span>{{ pI.consignee }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.pI.carriageBy')"></span>
          </dt>
          <dd>
            <span>{{ pI.carriageBy }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.pI.termsOfDelivery')"></span>
          </dt>
          <dd>
            <span>{{ pI.termsOfDelivery }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.pI.termsOfPayment')"></span>
          </dt>
          <dd>
            <span>{{ pI.termsOfPayment }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.pI.currency')"></span>
          </dt>
          <dd>
            <span>{{ pI.currency }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.pI.remarks')"></span>
          </dt>
          <dd>
            <span>{{ pI.remarks }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.pI.kingdeeId')"></span>
          </dt>
          <dd>
            <span>{{ pI.kingdeeId }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.pI.portOfDischarge')"></span>
          </dt>
          <dd>
            <span>{{ pI.portOfDischarge }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.pI.portOfLoading')"></span>
          </dt>
          <dd>
            <span>{{ pI.portOfLoading }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.pI.docStatus')"></span>
          </dt>
          <dd>
            <span>{{ pI.docStatus }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="pI.id" :to="{ name: 'PIEdit', params: { pIId: pI.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./pi-details.component.ts"></script>

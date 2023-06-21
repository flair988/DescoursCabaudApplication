<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="quotation">
        <h2 class="jh-entity-heading" data-cy="quotationDetailsHeading">
          <span v-text="t$('descoursCabaudApplicationApp.quotation.detail.title')"></span> {{ quotation.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.quotation.itemName')"></span>
          </dt>
          <dd>
            <span>{{ quotation.itemName }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.quotation.quotationDate')"></span>
          </dt>
          <dd>
            <span>{{ quotation.quotationDate }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.quotation.inquiryDocNumber')"></span>
          </dt>
          <dd>
            <span>{{ quotation.inquiryDocNumber }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.quotation.docNo')"></span>
          </dt>
          <dd>
            <span>{{ quotation.docNo }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.quotation.docStatus')"></span>
          </dt>
          <dd>
            <span>{{ quotation.docStatus }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.quotation.supplier')"></span>
          </dt>
          <dd>
            <span>{{ quotation.supplier }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.quotation.kingdeeId')"></span>
          </dt>
          <dd>
            <span>{{ quotation.kingdeeId }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="quotation.id"
          :to="{ name: 'QuotationEdit', params: { quotationId: quotation.id } }"
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

<script lang="ts" src="./quotation-details.component.ts"></script>

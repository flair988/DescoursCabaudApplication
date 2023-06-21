<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="purchaseReturn">
        <h2 class="jh-entity-heading" data-cy="purchaseReturnDetailsHeading">
          <span v-text="t$('descoursCabaudApplicationApp.purchaseReturn.detail.title')"></span> {{ purchaseReturn.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.purchaseReturn.itemName')"></span>
          </dt>
          <dd>
            <span>{{ purchaseReturn.itemName }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.purchaseReturn.kingdeeId')"></span>
          </dt>
          <dd>
            <span>{{ purchaseReturn.kingdeeId }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.purchaseReturn.docNumber')"></span>
          </dt>
          <dd>
            <span>{{ purchaseReturn.docNumber }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.purchaseReturn.supplier')"></span>
          </dt>
          <dd>
            <span>{{ purchaseReturn.supplier }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.purchaseReturn.supplierEmail')"></span>
          </dt>
          <dd>
            <span>{{ purchaseReturn.supplierEmail }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.purchaseReturn.materialReturnDate')"></span>
          </dt>
          <dd>
            <span>{{ purchaseReturn.materialReturnDate }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.purchaseReturn.reasonForReturn')"></span>
          </dt>
          <dd>
            <span>{{ purchaseReturn.reasonForReturn }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.purchaseReturn.purchaseDept')"></span>
          </dt>
          <dd>
            <span>{{ purchaseReturn.purchaseDept }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.purchaseReturn.remarks')"></span>
          </dt>
          <dd>
            <span>{{ purchaseReturn.remarks }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.purchaseReturn.docStatus')"></span>
          </dt>
          <dd>
            <span>{{ purchaseReturn.docStatus }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.purchaseReturn.itemCode')"></span>
          </dt>
          <dd>
            <span>{{ purchaseReturn.itemCode }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.purchaseReturn.supplierCode')"></span>
          </dt>
          <dd>
            <span>{{ purchaseReturn.supplierCode }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="purchaseReturn.id"
          :to="{ name: 'PurchaseReturnEdit', params: { purchaseReturnId: purchaseReturn.id } }"
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

<script lang="ts" src="./purchase-return-details.component.ts"></script>

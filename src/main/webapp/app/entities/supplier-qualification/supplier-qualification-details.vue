<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="supplierQualification">
        <h2 class="jh-entity-heading" data-cy="supplierQualificationDetailsHeading">
          <span v-text="t$('descoursCabaudApplicationApp.supplierQualification.detail.title')"></span> {{ supplierQualification.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.supplierQualification.itemName')"></span>
          </dt>
          <dd>
            <span>{{ supplierQualification.itemName }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.supplierQualification.cateGory')"></span>
          </dt>
          <dd>
            <span>{{ supplierQualification.cateGory }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.supplierQualification.supplier')"></span>
          </dt>
          <dd>
            <span>{{ supplierQualification.supplier }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.supplierQualification.date')"></span>
          </dt>
          <dd>
            <span>{{ supplierQualification.date }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.supplierQualification.supplierStatus')"></span>
          </dt>
          <dd>
            <span>{{ supplierQualification.supplierStatus }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.supplierQualification.evaluationStatus')"></span>
          </dt>
          <dd>
            <span>{{ supplierQualification.evaluationStatus }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.supplierQualification.businessLiabilityBopeScore')"></span>
          </dt>
          <dd>
            <span>{{ supplierQualification.businessLiabilityBopeScore }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.supplierQualification.comments')"></span>
          </dt>
          <dd>
            <span>{{ supplierQualification.comments }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="supplierQualification.id"
          :to="{ name: 'SupplierQualificationEdit', params: { supplierQualificationId: supplierQualification.id } }"
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

<script lang="ts" src="./supplier-qualification-details.component.ts"></script>

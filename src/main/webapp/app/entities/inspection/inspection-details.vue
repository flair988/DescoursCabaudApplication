<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="inspection">
        <h2 class="jh-entity-heading" data-cy="inspectionDetailsHeading">
          <span v-text="t$('descoursCabaudApplicationApp.inspection.detail.title')"></span> {{ inspection.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.inspection.itemName')"></span>
          </dt>
          <dd>
            <span>{{ inspection.itemName }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.inspection.kingdeeId')"></span>
          </dt>
          <dd>
            <span>{{ inspection.kingdeeId }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.inspection.supplierName')"></span>
          </dt>
          <dd>
            <span>{{ inspection.supplierName }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.inspection.email')"></span>
          </dt>
          <dd>
            <span>{{ inspection.email }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.inspection.inspectionDate')"></span>
          </dt>
          <dd>
            <span>{{ inspection.inspectionDate }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.inspection.cateGory')"></span>
          </dt>
          <dd>
            <span>{{ inspection.cateGory }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.inspection.qCResult')"></span>
          </dt>
          <dd>
            <span>{{ inspection.qCResult }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.inspection.docStatus')"></span>
          </dt>
          <dd>
            <span>{{ inspection.docStatus }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.inspection.inspectionType')"></span>
          </dt>
          <dd>
            <span>{{ inspection.inspectionType }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.inspection.inspectionBookingStatus')"></span>
          </dt>
          <dd>
            <span>{{ inspection.inspectionBookingStatus }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.inspection.inspectionEndDate')"></span>
          </dt>
          <dd>
            <span>{{ inspection.inspectionEndDate }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.inspection.supplierId')"></span>
          </dt>
          <dd>
            <span>{{ inspection.supplierId }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.inspection.reportNumber')"></span>
          </dt>
          <dd>
            <span>{{ inspection.reportNumber }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="inspection.id"
          :to="{ name: 'InspectionEdit', params: { inspectionId: inspection.id } }"
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

<script lang="ts" src="./inspection-details.component.ts"></script>

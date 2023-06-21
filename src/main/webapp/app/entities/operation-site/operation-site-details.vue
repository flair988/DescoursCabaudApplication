<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="operationSite">
        <h2 class="jh-entity-heading" data-cy="operationSiteDetailsHeading">
          <span v-text="t$('descoursCabaudApplicationApp.operationSite.detail.title')"></span> {{ operationSite.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSite.itemName')"></span>
          </dt>
          <dd>
            <span>{{ operationSite.itemName }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSite.linkSupplierFactory')"></span>
          </dt>
          <dd>
            <span>{{ operationSite.linkSupplierFactory }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSite.typeOfSite')"></span>
          </dt>
          <dd>
            <span>{{ operationSite.typeOfSite }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSite.contact')"></span>
          </dt>
          <dd>
            <span>{{ operationSite.contact }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSite.siteAddress')"></span>
          </dt>
          <dd>
            <span>{{ operationSite.siteAddress }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSite.cateGory')"></span>
          </dt>
          <dd>
            <span>{{ operationSite.cateGory }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSite.country')"></span>
          </dt>
          <dd>
            <span>{{ operationSite.country }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSite.kingdeeId')"></span>
          </dt>
          <dd>
            <span>{{ operationSite.kingdeeId }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSite.businessLicense')"></span>
          </dt>
          <dd>
            <span>{{ operationSite.businessLicense }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSite.sasDate')"></span>
          </dt>
          <dd>
            <span>{{ operationSite.sasDate }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSite.iso900ValidUtil')"></span>
          </dt>
          <dd>
            <span>{{ operationSite.iso900ValidUtil }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSite.iso14001ValidUtil')"></span>
          </dt>
          <dd>
            <span>{{ operationSite.iso14001ValidUtil }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSite.attributeLor')"></span>
          </dt>
          <dd>
            <span>{{ operationSite.attributeLor }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSite.siteQualification')"></span>
          </dt>
          <dd>
            <span>{{ operationSite.siteQualification }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSite.qualificationScore')"></span>
          </dt>
          <dd>
            <span>{{ operationSite.qualificationScore }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSite.pqvScore')"></span>
          </dt>
          <dd>
            <span>{{ operationSite.pqvScore }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSite.pqvDate')"></span>
          </dt>
          <dd>
            <span>{{ operationSite.pqvDate }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSite.pqvDecision')"></span>
          </dt>
          <dd>
            <span>{{ operationSite.pqvDecision }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSite.technicalAuditDate')"></span>
          </dt>
          <dd>
            <span>{{ operationSite.technicalAuditDate }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSite.technicalAuditScore')"></span>
          </dt>
          <dd>
            <span>{{ operationSite.technicalAuditScore }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSite.thirdRdPartyDate')"></span>
          </dt>
          <dd>
            <span>{{ operationSite.thirdRdPartyDate }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSite.thirdRdPartyScore')"></span>
          </dt>
          <dd>
            <span>{{ operationSite.thirdRdPartyScore }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSite.bopeDate')"></span>
          </dt>
          <dd>
            <span>{{ operationSite.bopeDate }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSite.bopeScore')"></span>
          </dt>
          <dd>
            <span>{{ operationSite.bopeScore }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSite.capRequired')"></span>
          </dt>
          <dd>
            <span>{{ operationSite.capRequired }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="operationSite.id"
          :to="{ name: 'OperationSiteEdit', params: { operationSiteId: operationSite.id } }"
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

<script lang="ts" src="./operation-site-details.component.ts"></script>

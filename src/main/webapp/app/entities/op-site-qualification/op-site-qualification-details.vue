<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="oPSiteQualification">
        <h2 class="jh-entity-heading" data-cy="oPSiteQualificationDetailsHeading">
          <span v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.detail.title')"></span> {{ oPSiteQualification.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.itemName')"></span>
          </dt>
          <dd>
            <span>{{ oPSiteQualification.itemName }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.cateGory')"></span>
          </dt>
          <dd>
            <span>{{ oPSiteQualification.cateGory }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.supplier')"></span>
          </dt>
          <dd>
            <span>{{ oPSiteQualification.supplier }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.date')"></span>
          </dt>
          <dd>
            <span>{{ oPSiteQualification.date }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.operationSite')"></span>
          </dt>
          <dd>
            <span>{{ oPSiteQualification.operationSite }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.attributedLoRForThisSite')"></span>
          </dt>
          <dd>
            <span>{{ oPSiteQualification.attributedLoRForThisSite }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.siteQualificationStatus')"></span>
          </dt>
          <dd>
            <span>{{ oPSiteQualification.siteQualificationStatus }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.csrResult')"></span>
          </dt>
          <dd>
            <span>{{ oPSiteQualification.csrResult }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.qualityProductionResult')"></span>
          </dt>
          <dd>
            <span>{{ oPSiteQualification.qualityProductionResult }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.businessLiabilityResult')"></span>
          </dt>
          <dd>
            <span>{{ oPSiteQualification.businessLiabilityResult }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.comments')"></span>
          </dt>
          <dd>
            <span>{{ oPSiteQualification.comments }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="oPSiteQualification.id"
          :to="{ name: 'OPSiteQualificationEdit', params: { oPSiteQualificationId: oPSiteQualification.id } }"
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

<script lang="ts" src="./op-site-qualification-details.component.ts"></script>

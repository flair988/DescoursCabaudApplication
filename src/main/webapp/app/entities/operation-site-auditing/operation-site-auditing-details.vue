<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="operationSiteAuditing">
        <h2 class="jh-entity-heading" data-cy="operationSiteAuditingDetailsHeading">
          <span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.detail.title')"></span> {{ operationSiteAuditing.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.itemName')"></span>
          </dt>
          <dd>
            <span>{{ operationSiteAuditing.itemName }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.cateGory')"></span>
          </dt>
          <dd>
            <span>{{ operationSiteAuditing.cateGory }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.supplier')"></span>
          </dt>
          <dd>
            <span>{{ operationSiteAuditing.supplier }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.operationSite')"></span>
          </dt>
          <dd>
            <span>{{ operationSiteAuditing.operationSite }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.linkSupplierFactory')"></span>
          </dt>
          <dd>
            <span>{{ operationSiteAuditing.linkSupplierFactory }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.typeOfSite')"></span>
          </dt>
          <dd>
            <span>{{ operationSiteAuditing.typeOfSite }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.auditingTool')"></span>
          </dt>
          <dd>
            <span>{{ operationSiteAuditing.auditingTool }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.auditingDate')"></span>
          </dt>
          <dd>
            <span>{{ operationSiteAuditing.auditingDate }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.csrResult')"></span>
          </dt>
          <dd>
            <span>{{ operationSiteAuditing.csrResult }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.qualityProductionResult')"></span>
          </dt>
          <dd>
            <span>{{ operationSiteAuditing.qualityProductionResult }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.businessLiabilityResult')"></span>
          </dt>
          <dd>
            <span>{{ operationSiteAuditing.businessLiabilityResult }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.comments')"></span>
          </dt>
          <dd>
            <span>{{ operationSiteAuditing.comments }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.issueDate')"></span>
          </dt>
          <dd>
            <span>{{ operationSiteAuditing.issueDate }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.dueDate')"></span>
          </dt>
          <dd>
            <span>{{ operationSiteAuditing.dueDate }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.closedDate')"></span>
          </dt>
          <dd>
            <span>{{ operationSiteAuditing.closedDate }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.closed')"></span>
          </dt>
          <dd>
            <span>{{ operationSiteAuditing.closed }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.status')"></span>
          </dt>
          <dd>
            <span>{{ operationSiteAuditing.status }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.capComments')"></span>
          </dt>
          <dd>
            <span>{{ operationSiteAuditing.capComments }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="operationSiteAuditing.id"
          :to="{ name: 'OperationSiteAuditingEdit', params: { operationSiteAuditingId: operationSiteAuditing.id } }"
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

<script lang="ts" src="./operation-site-auditing-details.component.ts"></script>

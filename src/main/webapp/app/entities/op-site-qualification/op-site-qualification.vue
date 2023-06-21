<template>
  <div>
    <h2 id="page-heading" data-cy="OPSiteQualificationHeading">
      <span v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.home.title')" id="op-site-qualification-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'OPSiteQualificationCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-op-site-qualification"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && oPSiteQualifications && oPSiteQualifications.length === 0">
      <span v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="oPSiteQualifications && oPSiteQualifications.length > 0">
      <table class="table table-striped" aria-describedby="oPSiteQualifications">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.itemName')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.cateGory')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.supplier')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.date')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.operationSite')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.attributedLoRForThisSite')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.siteQualificationStatus')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.csrResult')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.qualityProductionResult')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.businessLiabilityResult')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.comments')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="oPSiteQualification in oPSiteQualifications" :key="oPSiteQualification.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'OPSiteQualificationView', params: { oPSiteQualificationId: oPSiteQualification.id } }">{{
                oPSiteQualification.id
              }}</router-link>
            </td>
            <td>{{ oPSiteQualification.itemName }}</td>
            <td>{{ oPSiteQualification.cateGory }}</td>
            <td>{{ oPSiteQualification.supplier }}</td>
            <td>{{ oPSiteQualification.date }}</td>
            <td>{{ oPSiteQualification.operationSite }}</td>
            <td>{{ oPSiteQualification.attributedLoRForThisSite }}</td>
            <td>{{ oPSiteQualification.siteQualificationStatus }}</td>
            <td>{{ oPSiteQualification.csrResult }}</td>
            <td>{{ oPSiteQualification.qualityProductionResult }}</td>
            <td>{{ oPSiteQualification.businessLiabilityResult }}</td>
            <td>{{ oPSiteQualification.comments }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'OPSiteQualificationView', params: { oPSiteQualificationId: oPSiteQualification.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'OPSiteQualificationEdit', params: { oPSiteQualificationId: oPSiteQualification.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(oPSiteQualification)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="t$('entity.action.delete')"></span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <template #modal-title>
        <span
          id="descoursCabaudApplicationApp.oPSiteQualification.delete.question"
          data-cy="oPSiteQualificationDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-oPSiteQualification-heading"
          v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-oPSiteQualification"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeOPSiteQualification()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./op-site-qualification.component.ts"></script>

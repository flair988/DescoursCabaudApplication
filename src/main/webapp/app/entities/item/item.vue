<template>
  <div>
    <h2 id="page-heading" data-cy="ItemHeading">
      <span v-text="t$('descoursCabaudApplicationApp.item.home.title')" id="item-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('descoursCabaudApplicationApp.item.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ItemCreate' }" custom v-slot="{ navigate }">
          <button @click="navigate" id="jh-create-entity" data-cy="entityCreateButton" class="btn btn-primary jh-create-entity create-item">
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('descoursCabaudApplicationApp.item.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && items && items.length === 0">
      <span v-text="t$('descoursCabaudApplicationApp.item.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="items && items.length > 0">
      <table class="table table-striped" aria-describedby="items">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.item.itemStatus')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.item.itemFranceName')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.item.kingdeeId')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.item.itemName')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.item.codeag')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.item.technicalDocuments')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.item.certification')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.item.opportunitySheet')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.item.packingType')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.item.salePackageImage')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.item.cartonLengthMilimeter')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.item.cartonHeightMilimeter')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.item.barcode')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.item.cartonWeightKg')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.item.cartonWeightGr')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.item.cartonWidthMilimeter')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.item.productDescriptionAndFonctionalities')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.item.drawing')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.item.userManual')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.item.palletSize')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.item.typeOfMarketing')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.item.productPic')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.item.label')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.item.comment')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.item.productTaxonomy')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.item.netWeight')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.item.grossWeight')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.item.unitOfWeight')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.item.cartonVolumeMilimeter')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in items" :key="item.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ItemView', params: { itemId: item.id } }">{{ item.id }}</router-link>
            </td>
            <td>{{ item.itemStatus }}</td>
            <td>{{ item.itemFranceName }}</td>
            <td>{{ item.kingdeeId }}</td>
            <td>{{ item.itemName }}</td>
            <td>{{ item.codeag }}</td>
            <td>{{ item.technicalDocuments }}</td>
            <td>{{ item.certification }}</td>
            <td>{{ item.opportunitySheet }}</td>
            <td>{{ item.packingType }}</td>
            <td>{{ item.salePackageImage }}</td>
            <td>{{ item.cartonLengthMilimeter }}</td>
            <td>{{ item.cartonHeightMilimeter }}</td>
            <td>{{ item.barcode }}</td>
            <td>{{ item.cartonWeightKg }}</td>
            <td>{{ item.cartonWeightGr }}</td>
            <td>{{ item.cartonWidthMilimeter }}</td>
            <td>{{ item.productDescriptionAndFonctionalities }}</td>
            <td>{{ item.drawing }}</td>
            <td>{{ item.userManual }}</td>
            <td>{{ item.palletSize }}</td>
            <td>{{ item.typeOfMarketing }}</td>
            <td>{{ item.productPic }}</td>
            <td>{{ item.label }}</td>
            <td>{{ item.comment }}</td>
            <td>{{ item.productTaxonomy }}</td>
            <td>{{ item.netWeight }}</td>
            <td>{{ item.grossWeight }}</td>
            <td>{{ item.unitOfWeight }}</td>
            <td>{{ item.cartonVolumeMilimeter }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ItemView', params: { itemId: item.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ItemEdit', params: { itemId: item.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(item)"
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
          id="descoursCabaudApplicationApp.item.delete.question"
          data-cy="itemDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-item-heading" v-text="t$('descoursCabaudApplicationApp.item.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-item"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeItem()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./item.component.ts"></script>

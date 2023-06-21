<template>
  <div>
    <h2 id="page-heading" data-cy="ProductFinishedHeading">
      <span v-text="t$('descoursCabaudApplicationApp.productFinished.home.title')" id="product-finished-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('descoursCabaudApplicationApp.productFinished.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ProductFinishedCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-product-finished"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('descoursCabaudApplicationApp.productFinished.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && productFinisheds && productFinisheds.length === 0">
      <span v-text="t$('descoursCabaudApplicationApp.productFinished.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="productFinisheds && productFinisheds.length > 0">
      <table class="table table-striped" aria-describedby="productFinisheds">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.productFinished.itemName')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.productFinished.kingdeeId')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.productFinished.supplier')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.productFinished.supplierEmail')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.productFinished.orderDate')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.productFinished.cateGory')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.productFinished.remark')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.productFinished.materialReceiptDate')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.productFinished.docStatus')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.productFinished.supplierName')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="productFinished in productFinisheds" :key="productFinished.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ProductFinishedView', params: { productFinishedId: productFinished.id } }">{{
                productFinished.id
              }}</router-link>
            </td>
            <td>{{ productFinished.itemName }}</td>
            <td>{{ productFinished.kingdeeId }}</td>
            <td>{{ productFinished.supplier }}</td>
            <td>{{ productFinished.supplierEmail }}</td>
            <td>{{ productFinished.orderDate }}</td>
            <td>{{ productFinished.cateGory }}</td>
            <td>{{ productFinished.remark }}</td>
            <td>{{ productFinished.materialReceiptDate }}</td>
            <td>{{ productFinished.docStatus }}</td>
            <td>{{ productFinished.supplierName }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'ProductFinishedView', params: { productFinishedId: productFinished.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'ProductFinishedEdit', params: { productFinishedId: productFinished.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(productFinished)"
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
          id="descoursCabaudApplicationApp.productFinished.delete.question"
          data-cy="productFinishedDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-productFinished-heading"
          v-text="t$('descoursCabaudApplicationApp.productFinished.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-productFinished"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeProductFinished()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./product-finished.component.ts"></script>

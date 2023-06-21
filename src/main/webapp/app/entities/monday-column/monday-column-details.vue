<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="mondayColumn">
        <h2 class="jh-entity-heading" data-cy="mondayColumnDetailsHeading">
          <span v-text="t$('descoursCabaudApplicationApp.mondayColumn.detail.title')"></span> {{ mondayColumn.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.mondayColumn.boardId')"></span>
          </dt>
          <dd>
            <span>{{ mondayColumn.boardId }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.mondayColumn.columnId')"></span>
          </dt>
          <dd>
            <span>{{ mondayColumn.columnId }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.mondayColumn.title')"></span>
          </dt>
          <dd>
            <span>{{ mondayColumn.title }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.mondayColumn.type')"></span>
          </dt>
          <dd>
            <span>{{ mondayColumn.type }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="mondayColumn.id"
          :to="{ name: 'MondayColumnEdit', params: { mondayColumnId: mondayColumn.id } }"
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

<script lang="ts" src="./monday-column-details.component.ts"></script>

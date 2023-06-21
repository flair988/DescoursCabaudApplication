<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="processLog">
        <h2 class="jh-entity-heading" data-cy="processLogDetailsHeading">
          <span v-text="t$('descoursCabaudApplicationApp.processLog.detail.title')"></span> {{ processLog.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.processLog.type')"></span>
          </dt>
          <dd>
            <span>{{ processLog.type }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.processLog.request')"></span>
          </dt>
          <dd>
            <span>{{ processLog.request }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.processLog.response')"></span>
          </dt>
          <dd>
            <span>{{ processLog.response }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.processLog.status')"></span>
          </dt>
          <dd>
            <span>{{ processLog.status }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.processLog.reason')"></span>
          </dt>
          <dd>
            <span>{{ processLog.reason }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.processLog.processStartTime')"></span>
          </dt>
          <dd>
            <span>{{ processLog.processStartTime }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.processLog.processEndTime')"></span>
          </dt>
          <dd>
            <span>{{ processLog.processEndTime }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="processLog.id"
          :to="{ name: 'ProcessLogEdit', params: { processLogId: processLog.id } }"
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

<script lang="ts" src="./process-log-details.component.ts"></script>

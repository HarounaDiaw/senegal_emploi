import { MissingTranslationHandler, MissingTranslationHandlerParams, TranslateLoader } from '@ngx-translate/core';
import { HttpClient } from '@angular/common/http';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';

export const translationNotFoundMessage = 'translation-not-found';

export class MissingTranslationHandlerImpl implements MissingTranslationHandler {
  handle(params: MissingTranslationHandlerParams): string {
    return `${translationNotFoundMessage}[${params.key}]`;
  }
}

// FONCTION COMMENTÉE TEMPORAIREMENT
/*
export function translatePartialLoader(http: HttpClient): TranslateLoader {
  // Version corrigée : seulement HttpClient, aucun autre paramètre
  return new TranslateHttpLoader(http);
}
*/

export function missingTranslationHandler(): MissingTranslationHandler {
  return new MissingTranslationHandlerImpl();
}

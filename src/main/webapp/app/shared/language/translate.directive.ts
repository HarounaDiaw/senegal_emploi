import { MissingTranslationHandler, MissingTranslationHandlerParams, TranslateLoader } from '@ngx-translate/core';
import { HttpClient } from '@angular/common/http';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';

export const translationNotFoundMessage = 'translation-not-found';

export class MissingTranslationHandlerImpl implements MissingTranslationHandler {
  handle(params: MissingTranslationHandlerParams): string {
    return `${translationNotFoundMessage}[${params.key}]`;
  }
}

// FONCTION COMMENTÉE TEMPORAIREMENT - constructeur sans aucun paramètre
/*
export function translatePartialLoader(http: HttpClient): TranslateLoader {
  // Cette version n'accepte AUCUN paramètre
  return new TranslateHttpLoader();
}
*/

export function missingTranslationHandler(): MissingTranslationHandler {
  return new MissingTranslationHandlerImpl();
}

import { NgModule, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MissingTranslationHandler, TranslateLoader, TranslateModule, TranslateService } from '@ngx-translate/core';
import { missingTranslationHandler } from 'app/config/translation.config';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
@NgModule({
  imports: [
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: () => new TranslateHttpLoader(),
        deps: [HttpClient],
      },
      missingTranslationHandler: {
        provide: MissingTranslationHandler,
        useFactory: missingTranslationHandler,
      },
    }),
  ],
  exports: [TranslateModule],
})
export class TranslationModule {
  private translateService = inject(TranslateService);

  constructor() {
    this.translateService.setDefaultLang('en');
    const langKey = localStorage.getItem('locale') ?? 'en';
    this.translateService.use(langKey);
  }
}

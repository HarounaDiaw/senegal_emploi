import { ApplicationConfig, LOCALE_ID, importProvidersFrom, inject } from '@angular/core';
import { BrowserModule, Title } from '@angular/platform-browser';
import {
  NavigationError,
  Router,
  RouterFeatures,
  TitleStrategy,
  provideRouter,
  withComponentInputBinding,
  withDebugTracing,
  withNavigationErrorHandler,
} from '@angular/router';
import { ServiceWorkerModule } from '@angular/service-worker';
import { HttpClient, provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { NgbDateAdapter } from '@ng-bootstrap/ng-bootstrap';
import { FaIconLibrary } from '@fortawesome/angular-fontawesome';
import { faFacebookF, faTwitter, faLinkedinIn } from '@fortawesome/free-brands-svg-icons';
import { faMapMarkerAlt, faPhone, faEnvelope } from '@fortawesome/free-solid-svg-icons';
import { APP_INITIALIZER } from '@angular/core';
import { TranslateModule } from '@ngx-translate/core';

import './config/dayjs';
import { environment } from 'environments/environment';
import { httpInterceptorProviders } from './core/interceptor';
import routes from './app.routes';
import { NgbDateDayjsAdapter } from './config/datepicker-adapter';
import { AppPageTitleStrategy } from './app-page-title-strategy';
// import { TranslateHttpLoader } from '@ngx-translate/http-loader';

const routerFeatures: RouterFeatures[] = [
  withComponentInputBinding(),
  withNavigationErrorHandler((e: NavigationError) => {
    const router = inject(Router);
    if (e.error.status === 403) {
      router.navigate(['/accessdenied']);
    } else if (e.error.status === 404) {
      router.navigate(['/404']);
    } else if (e.error.status === 401) {
      router.navigate(['/login']);
    } else {
      router.navigate(['/error']);
    }
  }),
];

if (environment.DEBUG_INFO_ENABLED) {
  routerFeatures.push(withDebugTracing());
}

function initializeFontAwesome(library: FaIconLibrary) {
  library.addIcons(faFacebookF, faTwitter, faLinkedinIn, faMapMarkerAlt, faPhone, faEnvelope);
}

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes, ...routerFeatures),
    importProvidersFrom(BrowserModule),
    // Correction: Remplacer environment.production par !environment.DEBUG_INFO_ENABLED
    // Le service worker sera activé quand DEBUG_INFO_ENABLED est false (mode production)
    importProvidersFrom(ServiceWorkerModule.register('ngsw-worker.js', { enabled: !environment.DEBUG_INFO_ENABLED })),
    // Configuration minimale des traductions sans loader externe
    importProvidersFrom(
      TranslateModule.forRoot({
        fallbackLang: 'fr',
      }),
    ),
    provideHttpClient(withInterceptorsFromDi()),
    Title,
    { provide: LOCALE_ID, useValue: 'fr' },
    { provide: NgbDateAdapter, useClass: NgbDateDayjsAdapter },
    httpInterceptorProviders,
    { provide: TitleStrategy, useClass: AppPageTitleStrategy },
    {
      provide: APP_INITIALIZER,
      useFactory: () => {
        const iconLibrary = inject(FaIconLibrary);
        // Correction: initializeFontAwesome au lieu de initializeFolentAwesome
        return () => initializeFontAwesome(iconLibrary);
      },
      multi: true,
    },
  ],
};

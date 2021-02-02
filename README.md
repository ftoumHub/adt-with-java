<section>
        <h1 id="types-algébriques">Types algébriques</h1>
<p>Un type algébrique permet de combiner plusieurs types entre eux, en pouvant encoder à la fois les <em>types produit</em> (disposer d’un objet de type produit <span class="math inline"><span><span class="katex"><span class="katex-mathml"><math xmlns="http://www.w3.org/1998/Math/MathML"><semantics><mrow><mi>A</mi><mo>×</mo><mi>B</mi></mrow><annotation encoding="application/x-tex">A\times B</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height: 0.76666em; vertical-align: -0.08333em;"></span><span class="mord mathdefault">A</span><span class="mspace" style="margin-right: 0.222222em;"></span><span class="mbin">×</span><span class="mspace" style="margin-right: 0.222222em;"></span></span><span class="base"><span class="strut" style="height: 0.68333em; vertical-align: 0em;"></span><span class="mord mathdefault" style="margin-right: 0.05017em;">B</span></span></span></span></span></span> donne accès à un objet de type <span class="math inline"><span><span class="katex"><span class="katex-mathml"><math xmlns="http://www.w3.org/1998/Math/MathML"><semantics><mrow><mi>A</mi></mrow><annotation encoding="application/x-tex">A</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height: 0.68333em; vertical-align: 0em;"></span><span class="mord mathdefault">A</span></span></span></span></span></span> et un objet de type <span class="math inline"><span><span class="katex"><span class="katex-mathml"><math xmlns="http://www.w3.org/1998/Math/MathML"><semantics><mrow><mi>B</mi></mrow><annotation encoding="application/x-tex">B</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height: 0.68333em; vertical-align: 0em;"></span><span class="mord mathdefault" style="margin-right: 0.05017em;">B</span></span></span></span></span></span>) et les <em>types somme</em> (disposer d’un objet de type somme <span class="math inline"><span><span class="katex"><span class="katex-mathml"><math xmlns="http://www.w3.org/1998/Math/MathML"><semantics><mrow><mi>A</mi><mo>+</mo><mi>B</mi></mrow><annotation encoding="application/x-tex">A+B</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height: 0.76666em; vertical-align: -0.08333em;"></span><span class="mord mathdefault">A</span><span class="mspace" style="margin-right: 0.222222em;"></span><span class="mbin">+</span><span class="mspace" style="margin-right: 0.222222em;"></span></span><span class="base"><span class="strut" style="height: 0.68333em; vertical-align: 0em;"></span><span class="mord mathdefault" style="margin-right: 0.05017em;">B</span></span></span></span></span></span> donne accès soit à un objet de type <span class="math inline"><span><span class="katex"><span class="katex-mathml"><math xmlns="http://www.w3.org/1998/Math/MathML"><semantics><mrow><mi>A</mi></mrow><annotation encoding="application/x-tex">A</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height: 0.68333em; vertical-align: 0em;"></span><span class="mord mathdefault">A</span></span></span></span></span></span> soit un objet de type <span class="math inline"><span><span class="katex"><span class="katex-mathml"><math xmlns="http://www.w3.org/1998/Math/MathML"><semantics><mrow><mi>B</mi></mrow><annotation encoding="application/x-tex">B</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height: 0.68333em; vertical-align: 0em;"></span><span class="mord mathdefault" style="margin-right: 0.05017em;">B</span></span></span></span></span></span>).</p>
<p>Les types produits sont usuels dans la plupart des langages de programmation, à partir du moment où ils disposent de structure d’enregistrement (les différents champs d’une classe JAVA, les <code>struct</code> en C, <em>etc</em>.)</p>
<p>Par contre, les types sommes, en tant que disjonction entre différents sous-types, sont plus difficiles à voir dans ces langages. Il existe bien des opérateurs de disjonctions, comme le <code>switch</code> en JAVA, mais il n’opère pas une disjonction entre les <strong>types</strong>, mais entre les <strong>valeurs</strong></p>
<h1 id="cas-pratique">Cas pratique</h1>
<p>Supposons par exemple que l’on souhaite écrire une fonction qui retourne une valeur d’un certain type dans un cas, et une valeur d’un autre type dans un autre.</p>
<p>Dans la suite on va donner plusieurs encodages possibles en JAVA répondant à ce problème, et on verra quelles sont leurs limitations.</p>
<h2 id="enumérations">Enumérations</h2>
<p>Une première possibilité peut être d’utiliser les énumérations. Par exemple, on peut définir une classe <code>Resultat</code> utilisant un champ définissant le cas dans lequel on se trouve.</p>

```java
import static java.lang.Float.parseFloat;
import static org.demo.adt.step01.Cas.Droit;
import static org.demo.adt.step01.Cas.Gauche;
import static org.demo.adt.step01.Resultat.div;

enum Cas { Gauche, Droit }

class Resultat<A, B> {
    Cas cas;
    A a;
    B b;

    Resultat(Cas cas, A a, B b) {
        this.cas = cas;
        this.a = a;
        this.b = b;
    }

    static Resultat<Float, String> div(Float a, Float b) {
        if (b == 0.) {
            return new Resultat<>(Droit, null, "Impossible de diviser par zero");
        } else {
            return new Resultat<>(Gauche, a / b, null);
        }
    }
}

public class Step01 {
    public static void main(String[] args){
        Resultat<Float,String> resultat = div(parseFloat("2"), parseFloat("0"));
        switch (resultat.cas){
            case Gauche:
                System.out.println("resultat : " + resultat.a);
                break;
            case Droit:
                System.out.println(resultat.b);
                break;
            default:
                assert false;
        }
    }
}
```

<p>Le programme <code>div</code> renvoie la division de <code>a</code> par <code>b</code>, et si <code>b</code> est nul, il renvoie chaîne expliquant l’erreur.</p>
<p>Le problème de cette implémentation est qu’elle ne fait aucune vérification <strong>statique</strong> de cohérence de la structure, c’est-à-dire que sans exécuter le programme, je ne sais pas (ou plutôt, le compilateur ne me le dit pas) si le programme va s’exécuter sans erreur.</p>
<p>Deux types d’incohérences sont possibles</p>
<ol type="1">
<li><p>Lors de la <strong>création</strong> d’un résultat, le cas <code>Droit</code> ou <code>Gauche</code> n’est pas directement corrélé avec le contenu des autres paramètres. Il est par exemple possible de construire <code>Resultat(Gauche, null, "message")</code>.</p></li>
<li><p>Lors de la <strong>consultation</strong> du résultat, la disjonction des cas n’est pas corrélée avec les données auxquelles on a accès. Ainsi il est possible d’écrire le programme suivant sans erreur du compilateur.</p></li>
</ol>
<div class="sourceCode" id="cb2"><pre class="sourceCode java"><code class="sourceCode java"><a class="sourceLine" id="cb2-1" title="1"><span class="kw">public</span> <span class="dt">static</span> <span class="dt">void</span> <span class="fu">main</span>(<span class="bu">String</span>[] args){</a>
<a class="sourceLine" id="cb2-2" title="2">    Resultat&lt;<span class="bu">Float</span>,<span class="bu">String</span>&gt; resultat = <span class="fu">div</span>(<span class="bu">Float</span>.<span class="fu">parseFloat</span>(args[<span class="dv">0</span>]), <span class="bu">Float</span>.<span class="fu">parseFloat</span>(args[<span class="dv">1</span>]));</a>
<a class="sourceLine" id="cb2-3" title="3">    <span class="kw">switch</span> (resultat.<span class="fu">cas</span>){</a>
<a class="sourceLine" id="cb2-4" title="4">        <span class="kw">case</span> Droit:</a>
<a class="sourceLine" id="cb2-5" title="5">            <span class="bu">System</span>.<span class="fu">out</span>.<span class="fu">println</span>(<span class="st">"resultat : "</span> + resultat.<span class="fu">a</span>); <span class="co">// c'est pas corrélé au cas Droit</span></a>
<a class="sourceLine" id="cb2-6" title="6">            <span class="kw">break</span>;</a>
<a class="sourceLine" id="cb2-7" title="7">        <span class="kw">case</span> Gauche:</a>
<a class="sourceLine" id="cb2-8" title="8">            <span class="bu">System</span>.<span class="fu">out</span>.<span class="fu">println</span>(resultat.<span class="fu">b</span>); <span class="co">// n'est pas corrélé au cas Gauche</span></a>
<a class="sourceLine" id="cb2-9" title="9">            <span class="kw">break</span>;</a>
<a class="sourceLine" id="cb2-10" title="10">        <span class="kw">default</span>:</a>
<a class="sourceLine" id="cb2-11" title="11">            assert <span class="kw">false</span>; <span class="co">// On aimerait ici ne pas avoir à écrire ce cas</span></a>
<a class="sourceLine" id="cb2-12" title="12">    }</a>
<a class="sourceLine" id="cb2-13" title="13">}</a></code></pre></div>
<h2 id="sous-classes">Sous-classes</h2>
<p>Afin de toujours corréler les <code>cas</code> avec les données, on peut utiliser le sous-classage.</p>
<div class="sourceCode" id="cb3"><pre class="sourceCode java"><code class="sourceCode java"><a class="sourceLine" id="cb3-1" title="1"><span class="kw">abstract</span> <span class="kw">class</span> Resultat&lt;A, B&gt;{</a>
<a class="sourceLine" id="cb3-2" title="2">    <span class="kw">private</span> <span class="fu">Resultat</span>(){}</a>
<a class="sourceLine" id="cb3-3" title="3">    <span class="dt">static</span> <span class="dt">final</span> <span class="kw">class</span> Gauche&lt;A, B&gt; <span class="kw">extends</span> Resultat&lt;A, B&gt;{</a>
<a class="sourceLine" id="cb3-4" title="4">        A a;</a>
<a class="sourceLine" id="cb3-5" title="5">        <span class="fu">Gauche</span>(A a){</a>
<a class="sourceLine" id="cb3-6" title="6">            <span class="kw">super</span>();</a>
<a class="sourceLine" id="cb3-7" title="7">            <span class="kw">this</span>.<span class="fu">a</span> = a;</a>
<a class="sourceLine" id="cb3-8" title="8">        }</a>
<a class="sourceLine" id="cb3-9" title="9">    }</a>
<a class="sourceLine" id="cb3-10" title="10">    <span class="dt">static</span> <span class="dt">final</span> <span class="kw">class</span> Droit&lt;A, B&gt; <span class="kw">extends</span> Resultat&lt;A, B&gt;{</a>
<a class="sourceLine" id="cb3-11" title="11">        B b;</a>
<a class="sourceLine" id="cb3-12" title="12">        <span class="fu">Droit</span>(B b){</a>
<a class="sourceLine" id="cb3-13" title="13">            <span class="kw">super</span>();</a>
<a class="sourceLine" id="cb3-14" title="14">            <span class="kw">this</span>.<span class="fu">b</span> = b;</a>
<a class="sourceLine" id="cb3-15" title="15">        }</a>
<a class="sourceLine" id="cb3-16" title="16">    }</a>
<a class="sourceLine" id="cb3-17" title="17">}</a>
<a class="sourceLine" id="cb3-18" title="18">Resultat&lt;<span class="bu">Float</span>, <span class="bu">String</span>&gt; <span class="fu">div</span>(<span class="bu">Float</span> a, <span class="bu">Float</span> b){</a>
<a class="sourceLine" id="cb3-19" title="19">    <span class="kw">if</span>(b == <span class="fl">0.</span>){</a>
<a class="sourceLine" id="cb3-20" title="20">        <span class="kw">return</span> <span class="kw">new</span> Resultat.<span class="fu">Droit</span>&lt;&gt;(<span class="st">"Impossible de diviser par zero"</span>);</a>
<a class="sourceLine" id="cb3-21" title="21">    } <span class="kw">else</span> {</a>
<a class="sourceLine" id="cb3-22" title="22">        <span class="kw">return</span> <span class="kw">new</span> Resultat.<span class="fu">Gauche</span>&lt;&gt;(a/b);</a>
<a class="sourceLine" id="cb3-23" title="23">    }</a>
<a class="sourceLine" id="cb3-24" title="24">}</a>
<a class="sourceLine" id="cb3-25" title="25"><span class="kw">public</span> <span class="dt">static</span> <span class="dt">void</span> <span class="fu">main</span>(<span class="bu">String</span>[] args){</a>
<a class="sourceLine" id="cb3-26" title="26">    Resultat&lt;<span class="bu">Float</span>,<span class="bu">String</span>&gt; resultat = <span class="fu">div</span>(<span class="bu">Float</span>.<span class="fu">parseFloat</span>(args[<span class="dv">0</span>]), <span class="bu">Float</span>.<span class="fu">parseFloat</span>(args[<span class="dv">1</span>]));</a>
<a class="sourceLine" id="cb3-27" title="27">    <span class="kw">if</span> (resultat <span class="kw">instanceof</span> Gauche){</a>
<a class="sourceLine" id="cb3-28" title="28">        Gauche&lt;<span class="bu">Float</span>,<span class="bu">String</span>&gt; gauche = (Gauche&lt;<span class="bu">Float</span>, <span class="bu">String</span>&gt;) resultat;</a>
<a class="sourceLine" id="cb3-29" title="29">        <span class="bu">System</span>.<span class="fu">out</span>.<span class="fu">println</span>(<span class="st">"resultat : "</span> + gauche.<span class="fu">a</span>);</a>
<a class="sourceLine" id="cb3-30" title="30">    } <span class="kw">else</span> {</a>
<a class="sourceLine" id="cb3-31" title="31">        Droit&lt;<span class="bu">Float</span>,<span class="bu">String</span>&gt; droit = (Droit&lt;<span class="bu">Float</span>, <span class="bu">String</span>&gt;) resultat;</a>
<a class="sourceLine" id="cb3-32" title="32">        <span class="bu">System</span>.<span class="fu">out</span>.<span class="fu">println</span>(droit.<span class="fu">b</span>);</a>
<a class="sourceLine" id="cb3-33" title="33">    }</a>
<a class="sourceLine" id="cb3-34" title="34">}</a></code></pre></div>
<p>On notera l’usage du constructeur privé afin d’emplêcher le sous-classage excepté des classes <code>Gauche</code> et <code>Droit</code>, ainsi que les modificateurs <code>final</code> devant ces deux classes, afin là aussi d’empécher le sous-classage. L’important de cette interdiction apparaît dans la fonction principale, où l’on teste l’appartenance du résultat à une classe ou l’autre.</p>
<p>Ici, le premier problème de cohérence de structure a été résolu, car on ne peut plus faire de résultat incohérent. Par contre, il reste toujours le problème de cohérence à l’utilisation, car chaque branche du test <code>instanceof</code> peut ne pas être cohérent avec le résultat du test.</p>
<h2 id="utilisation-cohérente">Utilisation cohérente</h2>
<p>Tout en gardant l’idée de la sous-classe pour garder la cohérence à la construction, on propose cette modification pour permettre de conserver le test de cohérence lors de l’utilisation.</p>
<div class="sourceCode" id="cb4"><pre class="sourceCode java"><code class="sourceCode java"><a class="sourceLine" id="cb4-1" title="1"><span class="kw">abstract</span> <span class="kw">class</span> Resultat&lt;A, B&gt;{</a>
<a class="sourceLine" id="cb4-2" title="2">    <span class="kw">private</span> <span class="fu">Resultat</span>(){}</a>
<a class="sourceLine" id="cb4-3" title="3">    <span class="kw">public</span> <span class="kw">interface</span> Cases&lt;R, A, B&gt;{</a>
<a class="sourceLine" id="cb4-4" title="4">        R <span class="fu">casGauche</span>(A gauche);</a>
<a class="sourceLine" id="cb4-5" title="5">        R <span class="fu">casDroit</span>(B droit);</a>
<a class="sourceLine" id="cb4-6" title="6">    }</a>
<a class="sourceLine" id="cb4-7" title="7">    <span class="kw">abstract</span> &lt;R&gt; R <span class="fu">match</span>(Cases&lt;R, A, B&gt; cases);</a>
<a class="sourceLine" id="cb4-8" title="8">    <span class="dt">static</span> <span class="dt">final</span> <span class="kw">class</span> Gauche&lt;A, B&gt; <span class="kw">extends</span> Resultat&lt;A, B&gt;{</a>
<a class="sourceLine" id="cb4-9" title="9">        A a;</a>
<a class="sourceLine" id="cb4-10" title="10">        <span class="fu">Gauche</span>(A a){</a>
<a class="sourceLine" id="cb4-11" title="11">            <span class="kw">super</span>();</a>
<a class="sourceLine" id="cb4-12" title="12">            <span class="kw">this</span>.<span class="fu">a</span> = a;</a>
<a class="sourceLine" id="cb4-13" title="13">        }</a>
<a class="sourceLine" id="cb4-14" title="14">        &lt;R&gt; R <span class="fu">match</span>(Cases&lt;R, A, B&gt; cases) {</a>
<a class="sourceLine" id="cb4-15" title="15">            <span class="kw">return</span> cases.<span class="fu">casGauche</span>(a);</a>
<a class="sourceLine" id="cb4-16" title="16">        }</a>
<a class="sourceLine" id="cb4-17" title="17">    }</a>
<a class="sourceLine" id="cb4-18" title="18">    <span class="dt">static</span> <span class="dt">final</span> <span class="kw">class</span> Droit&lt;A, B&gt; <span class="kw">extends</span> Resultat&lt;A, B&gt;{</a>
<a class="sourceLine" id="cb4-19" title="19">        B b;</a>
<a class="sourceLine" id="cb4-20" title="20">        <span class="fu">Droit</span>(B b){</a>
<a class="sourceLine" id="cb4-21" title="21">            <span class="kw">super</span>();</a>
<a class="sourceLine" id="cb4-22" title="22">            <span class="kw">this</span>.<span class="fu">b</span> = b;</a>
<a class="sourceLine" id="cb4-23" title="23">        }</a>
<a class="sourceLine" id="cb4-24" title="24">        &lt;R&gt; R <span class="fu">match</span>(Cases&lt;R, A, B&gt; cases) {</a>
<a class="sourceLine" id="cb4-25" title="25">            <span class="kw">return</span> cases.<span class="fu">casDroit</span>(b);</a>
<a class="sourceLine" id="cb4-26" title="26">        }</a>
<a class="sourceLine" id="cb4-27" title="27">    }</a>
<a class="sourceLine" id="cb4-28" title="28">    <span class="dt">static</span> Resultat&lt;<span class="bu">Float</span>, <span class="bu">String</span>&gt; <span class="fu">div</span>(<span class="bu">Float</span> a, <span class="bu">Float</span> b){</a>
<a class="sourceLine" id="cb4-29" title="29">        <span class="kw">if</span> (b == <span class="fl">0.</span>){</a>
<a class="sourceLine" id="cb4-30" title="30">            <span class="kw">return</span> <span class="kw">new</span> Droit&lt;&gt;(<span class="st">"Impossible de diviser par zero"</span>);</a>
<a class="sourceLine" id="cb4-31" title="31">        } <span class="kw">else</span> {</a>
<a class="sourceLine" id="cb4-32" title="32">            <span class="kw">return</span> <span class="kw">new</span> Gauche&lt;&gt;(a/b);</a>
<a class="sourceLine" id="cb4-33" title="33">        }</a>
<a class="sourceLine" id="cb4-34" title="34">    }</a>
<a class="sourceLine" id="cb4-35" title="35">    <span class="kw">public</span> <span class="dt">static</span> <span class="dt">void</span> <span class="fu">main</span>(<span class="bu">String</span>[] args) {</a>
<a class="sourceLine" id="cb4-36" title="36">        Resultat&lt;<span class="bu">Float</span>, <span class="bu">String</span>&gt; res = <span class="fu">div</span>(<span class="bu">Float</span>.<span class="fu">parseFloat</span>(args[<span class="dv">0</span>], args[<span class="dv">1</span>]));</a>
<a class="sourceLine" id="cb4-37" title="37">        res.<span class="fu">match</span>(<span class="kw">new</span> Cases&lt;<span class="bu">Void</span>, <span class="bu">Float</span>, <span class="bu">String</span>&gt;() {</a>
<a class="sourceLine" id="cb4-38" title="38">            <span class="kw">public</span> <span class="bu">Void</span> <span class="fu">casGauche</span>(<span class="bu">Float</span> gauche) {</a>
<a class="sourceLine" id="cb4-39" title="39">                <span class="bu">System</span>.<span class="fu">out</span>.<span class="fu">println</span>(<span class="st">"res : "</span> + gauche);</a>
<a class="sourceLine" id="cb4-40" title="40">                <span class="kw">return</span> <span class="kw">null</span>;</a>
<a class="sourceLine" id="cb4-41" title="41">            }</a>
<a class="sourceLine" id="cb4-42" title="42">            <span class="kw">public</span> <span class="bu">Void</span> <span class="fu">casDroit</span>(<span class="bu">String</span> droit) {</a>
<a class="sourceLine" id="cb4-43" title="43">                <span class="bu">System</span>.<span class="fu">out</span>.<span class="fu">println</span>(droit);</a>
<a class="sourceLine" id="cb4-44" title="44">                <span class="kw">return</span> <span class="kw">null</span>;</a>
<a class="sourceLine" id="cb4-45" title="45">            }</a>
<a class="sourceLine" id="cb4-46" title="46">        });</a>
<a class="sourceLine" id="cb4-47" title="47">    }</a>
<a class="sourceLine" id="cb4-48" title="48">}</a></code></pre></div>
<p>Ici, à la fois la cohérence à l’écriture et à la consultation sont testés lors de la compilation.</p>
    </section>